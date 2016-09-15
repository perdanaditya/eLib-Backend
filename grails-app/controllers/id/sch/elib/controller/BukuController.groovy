package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Buku;
import id.sch.services.BukuService;

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class BukuController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def BukuService

    def index() {
        render BukuService.fetchList(false, 0) as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!BukuService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!BukuService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Buku buku = Buku.findById(id.toLong())

        render buku as JSON
    }
    
    def exportToExcel(){
        def mode = params.mode
        def tahun = params.tahun
        if (!tahun){
            tahun = 0
        }
        println "MODE: "+mode
        println "TAHUN: "+tahun
        XSSFWorkbook workbook = bukuService.download(mode.toLong(), tahun.toLong())
        
        //write test file
        try {
            String filename="document.xlsx"
            File fileToWrite = new File("./assets/"+filename)
            if(fileToWrite.exists()){
                fileToWrite.delete();
            }
            
            //create new file
            fileToWrite.createNewFile()
            FileOutputStream out = new FileOutputStream(fileToWrite);
            workbook.write(out);
            out.close()
            
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
            response.setHeader("Content-disposition", "attachment;filename=Daftar Buku.xlsx")
            response.outputStream << fileToWrite.bytes
			
        } catch (FileNotFoundException e) {
            println "FileNotFoundException: "+e.getMessage()
        } catch (IOException e) {
            println "IOException: "+e.getMessage()
        }
        
//        def output = ["message": "success"]
//        render output as JSON
    }
}
