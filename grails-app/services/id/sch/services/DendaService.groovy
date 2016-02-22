package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Denda
import java.sql.Timestamp
import java.text.SimpleDateFormat
@Transactional
class DendaService {
    
def fetchList() {
        return Denda.findAll()
    }
    
    boolean save(Object obj) {
        Denda out = new Denda(
            nominal: obj.nominal,
            tanggal: obj.tanggal,
            active: obj.active,
            userInput: obj.userInput
        )
        out.inputTime = new Timestamp(new java.util.Date().getTime())

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Denda.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.nominal= obj.nominal
            out.tanggal= obj.tanggal
            out.active=obj.active
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
