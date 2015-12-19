package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Penerbit
import java.sql.Timestamp
import java.text.SimpleDateFormat

@Transactional
class PenerbitService {

    def fetchList() {
        return Penerbit.findAll()
    }
    
    boolean save(Object obj) {
        Penerbit out = new Penerbit(
            namaPenerbit: obj.namaPenerbit,
            alamatPenerbit: obj.alamatPenerbit,
            kotaPenerbit: obj.kotaPenerbit,
            telponPenerbit: obj.telponPenerbit,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )
        out.inputTime = new Timestamp(new java.util.Date().getTime())

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Penerbit.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.namaPenerbit= obj.namaPenerbit
            out.alamatPenerbit= obj.alamatPenerbit
            out.kotaPenerbit= obj.kotaPenerbit
            out.telponPenerbit= obj.telponPenerbit
            out.active=obj.active
            out.userInput=obj.userInput
            //            out.inputTime=obj.inputTime
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
