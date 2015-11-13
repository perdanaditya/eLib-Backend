package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Peminjaman

@Transactional
class PeminjamanService {

    def fetchList() {
        return Buku.findAll()
    }
    
    boolean save(Object obj) {
        Peminjaman out = new Peminjaman(
            buku: obj.buku,
            user: obj.user,
            tanggalPinjam: obj.tanggalPinjam,
            masaPinjam: obj.masaPinjam,
            tanggalPengembalian: obj.tanggalPengembalian,
            totalDenda: obj.totalDenda
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Peminjaman.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.buku= obj.buku
            out.user= obj.user
            out.tanggalPinjam= obj.tanggalPinjam
            out.masaPinjam= obj.masaPinjam
            out.tanggalPengembalian= obj.tanggalPengembalian
            out.totalDenda= obj.totalDenda
        }
        return out.save(failOnError: true)
    }
}
