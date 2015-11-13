package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Buku

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
            rakBuku: obj.rakBuku
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Buku.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.isbn= obj.isbn
            out.penerbit= obj.penerbit
            out.judul= obj.judul
            out.stock= obj.stock
            out.tahunTerbit= obj.tahunTerbit
            out.cover= obj.cover
            out.rakBuku= obj.rakBuku
        }
        return out.save(failOnError: true)
    }
}
