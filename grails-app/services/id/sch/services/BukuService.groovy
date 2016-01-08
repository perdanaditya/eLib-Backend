package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Buku
import id.sch.elib.model.Penerbit
import id.sch.elib.model.RakBuku
import java.sql.Timestamp

@Transactional
class BukuService {

    def fetchList() {
        return Buku.findAll()
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
            active: obj.active,
            userInput:obj.userInput,
            inputTime:obj.inputTime
        )

        out.inputTime = new Timestamp(new java.util.Date().getTime())
        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Buku.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.isbn= obj.isbn
            out.penerbit= Penerbit.get(obj.penerbit.id)
            out.judul= obj.judul
            out.stock= obj.stock
            out.tahunTerbit= obj.tahunTerbit
            out.cover= obj.cover
            out.rakBuku= RakBuku.get(obj.rakBuku.id)
            out.active=obj.active
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
