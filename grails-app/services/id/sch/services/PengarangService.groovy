package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Pengarang
import java.sql.Timestamp
import java.text.SimpleDateFormat

@Transactional
class PengarangService {

    def fetchList() {
        return Pengarang.findAll()
    }
    
    boolean save(Object obj) {
        Pengarang out = new Pengarang(
            namaPengarang: obj.namaPengarang,
            active: true,
            userInput: obj.userInput
        )
        out.inputTime = new Timestamp(new java.util.Date().getTime())

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Pengarang.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.namaPengarang= obj.namaPengarang
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
