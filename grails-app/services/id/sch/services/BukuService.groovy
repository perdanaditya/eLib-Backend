package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Buku
import id.sch.elib.model.Penerbit
import id.sch.elib.model.RakBuku
import java.sql.Timestamp

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class BukuService {

    def fetchList() {
        def c = Buku.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
//            createAlias "buku","b"
//                
//            if (params.status){
//                ilike("status",'%'+params.status+'%')
//            }
//            if (params.penerimaan){
//                eq("p.id", params.penerimaan.toLong())
//            }
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
}
