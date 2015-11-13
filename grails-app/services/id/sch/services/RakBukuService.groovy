package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.RakBuku

@Transactional
class RakBukuService {

    def fetchList() {
        return RakBuku.findAll()
    }
    
    boolean save(Object obj) {
        RakBuku out = new RakBuku(
            namaRak: obj.namaRak
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = RakBuku.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.namaRak= obj.namaRak
        }
        return out.save(failOnError: true)
    }
}
