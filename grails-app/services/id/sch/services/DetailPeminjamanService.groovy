package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.DetailPeminjaman
import java.sql.Timestamp
import java.text.SimpleDateFormat

@Transactional
class DetailPeminjamanService {
    
    def fetchList() {
        return DetailPeminjaman.findAll()
    }
    
    boolean save(Object obj) {
        DetailPeminjaman out = new DetailPeminjaman(
            peminjaman: Peminjaman.get(obj.peminjaman.id.toLong()),
            buku: Buku.get(obj.buku.id.toLong()),
            tanggalPengembalian: obj.tanggalPengembalian,
            denda: obj.denda,
            active:true,
            userInput: obj.userInput
        )
        out.inputTime = new Timestamp(new java.util.Date().getTime())

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = DetailPeminjaman.findById(obj.id.toLong())
        if (out != null) {
            out.peminjaman= Peminjaman.get(obj.peminjaman.id.toLong())
            out.buku= Buku.get(obj.buku.id.toLong())
            out.tanggalPengembalian= obj.tanggalPengembalian
            out.denda= obj.denda
            out.active=obj.active
            out.userInput= obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
