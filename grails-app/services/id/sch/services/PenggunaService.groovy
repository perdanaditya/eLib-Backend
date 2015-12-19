package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Pengguna

@Transactional
class PenggunaService {

    def fetchList() {
        return Pengguna.findAll()
    }
    
    boolean save(Object obj) {
        Pengguna out = new Pengguna(
            noInduk: obj.noInduk,
            nama: obj.nama,
            jabatan: obj.jabatan,
            tempatLahir: obj.tempatLahir,
            tanggalLahir: obj.tanggalLahir,
            jenisKelamin: obj.jenisKelamin,
            alamat: obj.alamat,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Pengguna.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.noInduk = obj.noInduk
            out.nama = obj.nama
            out.jabatan = obj.jabatan
            out.tempatLahir = obj.tempatLahir
            out.tanggalLahir = obj.tanggalLahir
            out.jenisKelamin = obj.jenisKelamin
            out.alamat = obj.alamat
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime= obj.inputTime
        }
        return out.save(failOnError: true)
    }
}
