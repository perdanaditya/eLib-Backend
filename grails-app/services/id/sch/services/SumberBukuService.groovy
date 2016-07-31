package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.SumberBuku
import id.sch.elib.model.Buku
import id.sch.elib.model.Pengguna
import java.sql.Timestamp
import java.text.SimpleDateFormat

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class SumberBukuService {

    def fetchList() {
        //        return SumberBuku.findAll()

        def c = SumberBuku.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                groupProperty("id","id")
                groupProperty("buku","buku")
                groupProperty("pengguna","pengguna")
                groupProperty("namaPemberi","namaPemberi")
                groupProperty("jumlah","jumlah")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                buku : [
                    id : it.buku.id,
                    isbn : it.buku.isbn,
                    judul : it.buku.judul
                ],
                pengguna : [
                    id : it.pengguna ? it.pengguna.id : 0,
                    noInduk :it.pengguna ? it.pengguna.noInduk : "",
                    nama : it.pengguna ? it.pengguna.nama : ""
                ],
                namaPemberi : it.namaPemberi,
                jumlah : it.jumlah,
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        SumberBuku out = new SumberBuku(
            buku: Buku.get(obj.buku.id.toLong()),
            pengguna: Pengguna.get(obj.pengguna.id.toLong()),
            namaPemberi: obj.namaPemberi,
            jumlah: obj.jumlah,
            active: true,
            userInput: obj.userInput
        )
        out.inputTime = new Timestamp(new java.util.Date().getTime())

        if(out.save(failOnError: true)){
            def buku = Buku.get(obj.buku.id.toLong())
            buku.stock=buku.stock+obj.jumlah
            buku.userInput= obj.userInput
            buku.inputTime = new Timestamp(new java.util.Date().getTime())
            return buku.save(failOnError: true)
        }else{
            println out.errors.allErrors
            return false
        }
    }
    
    boolean update(Object obj) {
        def out = SumberBuku.findById(obj.id.toLong())
        if (out != null) {
            out.buku= Buku.get(obj.buku.id.toLong())
            out.pengguna= Pengguna.get(obj.pengguna.id.toLong())
            out.namaPemberi=obj.namaPemberi
            out.jumlah= obj.jumlah
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
