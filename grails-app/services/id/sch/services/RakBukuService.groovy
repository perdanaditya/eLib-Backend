package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.RakBuku
import java.sql.Timestamp

@Transactional
class RakBukuService {

    def fetchList() {
        return RakBuku.findAll()
    }
    
    boolean save(Object obj) {
        RakBuku out = new RakBuku(
            namaJenis: obj.namaJenis,
            noDdc: obj.noDdc,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )

        out.inputTime = new Timestamp(new java.util.Date().getTime())
        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = RakBuku.findById(obj.id.toLong())
        if (out != null) {
            out.namaJenis= obj.namaJenis
            out.noDdc=obj.noDdc
            out.active=obj.active
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
