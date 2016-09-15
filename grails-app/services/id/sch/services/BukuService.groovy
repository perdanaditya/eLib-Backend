package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Buku
import id.sch.elib.model.Penerbit
import id.sch.elib.model.RakBuku
import id.sch.elib.model.SumberBuku
import id.sch.elib.model.DetailPengarang
import java.sql.Timestamp

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFDataFormat
import org.apache.poi.ss.usermodel.Cell

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class BukuService {

    def fetchList(boolean active, long tahun) {
        def c = Buku.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            //            createAlias "buku","b"
            //                
            if (active){
                eq("active",active)
            }
            if (tahun != 0){
                eq("tahunTerbit", tahun+"")
            }
            //                
            projections{
                groupProperty("id","id")
                groupProperty("isbn","isbn")
                groupProperty("penerbit","penerbit")
                groupProperty("judul","judul")
                groupProperty("stock","stock")
                groupProperty("tahunTerbit","tahunTerbit")
                groupProperty("cover","cover")
                groupProperty("rakBuku","rakBuku")
                groupProperty("masaPinjam","masaPinjam")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                isbn : it.isbn,
                penerbit : [
                    id : it.penerbit.id,
                    namaPenerbit : it.penerbit.namaPenerbit
                ],
                judul : it.judul,
                stock : it.stock,
                tahunTerbit : it.tahunTerbit,
                cover : it.cover,
                rakBuku : [
                    id : it.rakBuku.id,
                    namaJenis : it.rakBuku.namaJenis,
                    noDdc : it.rakBuku.noDdc
                ],
                masaPinjam: it.masaPinjam,
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        Buku out = new Buku(
            isbn: obj.isbn,
            penerbit: obj.penerbit,
            judul: obj.judul,
            stock: obj.stock,
            tahunTerbit: obj.tahunTerbit,
            cover: obj.cover,
            rakBuku: obj.rakBuku,
            masaPinjam: obj.masaPinjam,
            active: obj.active,
            userInput:obj.userInput,
            inputTime:obj.inputTime
        )

        out.inputTime = new Timestamp(new java.util.Date().getTime())
        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Buku.findById(obj.id.toLong())
        if (out != null) {
            out.isbn= obj.isbn
            out.penerbit= Penerbit.get(obj.penerbit.id)
            out.judul= obj.judul
            out.stock= obj.stock
            out.tahunTerbit= obj.tahunTerbit
            out.cover= obj.cover
            out.rakBuku= RakBuku.get(obj.rakBuku.id)
            out.masaPinjam= obj.masaPinjam
            out.active=obj.active
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
    
    def download(long mode, long tahun){
        File file = new File("./Template/Laporan Daftar Buku.xlsx")

        InputStream stream = new FileInputStream(file)
        XSSFWorkbook workbook = new XSSFWorkbook(stream)
        XSSFSheet sheet
        
        if(file.exists()){
            def listBuku = fetchList(true, tahun)
            int startReadCol = 1
            int maxColumn = 11
            int sampleRow = 12
            int startInsertRow = sampleRow
            if(listBuku.size()>0){
                sheet = workbook.getSheet("BI");
                if(sheet){
                    //ambil style dan format sesuai sample row di template
                    XSSFRow row = sheet.getRow(sampleRow);
                    ArrayList<XSSFCellStyle> styleList = new ArrayList<>();
                    
                    for (int i = 0; i < maxColumn - startReadCol; i++) {//loop ambil style per cell
                        XSSFCellStyle style = row.getCell((i + startReadCol)).getCellStyle()
                        styleList.add(style)
                    }

                    //untuk menyisipkan (insert) row baru di block yang mau di generate
                    int rows = sheet.getLastRowNum();
                    if(listBuku.size()>1){
                        sheet.shiftRows((startInsertRow), rows, listBuku.size() - 1);
                    }
                    //di -1 karena row di template defaultnya 1
                    
                    listBuku.eachWithIndex{ it, index ->//loop row
                        //isi row yang tadi sudah di sisipkan diatas
                        row = sheet.createRow((startInsertRow + index));
                        def singleBuku = Buku.get(it.id.toLong())
                        ArrayList<String> itemList = (ArrayList<String>) toArrayBuku(singleBuku)
                    
                        for (int i = startReadCol; i < maxColumn; i++) {//ini loop buat column
                            Cell cell = row.createCell(i);
                            //isi masing masing cell dengan style dan value masing masing
                            switch (i){
                            case startReadCol: 
                                //diisi nomor (auto increment)
                                cell.setCellValue((index + 1) + ". ")
                                break
                            default:
                                cell.setCellValue(itemList.get(i-startReadCol))
                                break;
                            }
                            cell.setCellStyle(styleList.get(i - startReadCol));
                        }
                        
                    }
                }
            }
            for (int i = 0; i < maxColumn - startReadCol; i++) {
                sheet.autoSizeColumn(i)
            }
        }
        return workbook
    }
    
    //method buat ngurutin data berdasarkan template excel
    def toArrayBuku(Buku bukuParam){
        //fetch pengarang by buku
        def listPengarang = ""
        def c = DetailPengarang.createCriteria()
        def selected = c.list{
            buku{
                eq('id', bukuParam.id)
            }
            
        }
        def selectedData = selected.collect{
            [
                namaPengarang: it.pengarang.namaPengarang
            ]
        }
        selectedData.eachWithIndex{ item, index ->
            if(index==0){
                listPengarang = item.namaPengarang
            }else{
                listPengarang = listPengarang+", "+item.namaPengarang
            }
        }
        
        //fetch sumber by buku
        def listSumber = ""
        c = SumberBuku.createCriteria()
        selected = c.list{
            buku{
                eq('id', bukuParam.id)
            }
            
        }
        
        selectedData = selected.collect{
            [
                pengguna: (it.pengguna!=null? it.pengguna.nama:null),
                namaPemberi: it.namaPemberi
            ]
        }
        selectedData.eachWithIndex{ item, index ->
            if(index==0){
                listSumber = (item.pengguna!=null? item.pengguna:item.namaPemberi)
            }else{
                listSumber = listSumber+", "+(item.pengguna!=null? item.pengguna:item.namaPemberi)
            }
        }
        
        def arrayBuku = new ArrayList<String>();
        arrayBuku.add("-")
        arrayBuku.add(bukuParam.isbn)
        arrayBuku.add(bukuParam.inputTime.toString())
        arrayBuku.add(bukuParam.rakBuku.noDdc)
        arrayBuku.add(listPengarang)
        arrayBuku.add(bukuParam.judul)
        arrayBuku.add(bukuParam.penerbit.namaPenerbit)
        arrayBuku.add(bukuParam.tahunTerbit)
        arrayBuku.add(listSumber)
        arrayBuku.add(bukuParam.stock.toString())
        return arrayBuku
    }
}
