package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Pengguna
import id.sch.elib.model.Pengunjung
import java.sql.Timestamp

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class PengunjungService {

    def fetchList() {
        def c = Pengunjung.createCriteria()
        def selected = c.list{
            order("id","desc")
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)  
            projections{
                groupProperty("id","id")
                groupProperty("pengguna","pengguna")
                groupProperty("tujuan","tujuan")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                pengguna : [
                    id : it.pengguna.id,
                    nama : it.pengguna.nama,
                    noInduk : it.pengguna.noInduk
                ],
                tujuan : it.tujuan,
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        Pengunjung out = new Pengunjung(
            pengguna: obj.pengguna,
            tujuan: obj.tujuan,
            active: obj.active,
            userInput:obj.userInput,
            inputTime:obj.inputTime
        )

        out.inputTime = new Timestamp(new java.util.Date().getTime())
        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Pengunjung.findById(obj.id.toLong())
        if (out != null) {
            out.pengguna= Pengguna.get(obj.pengguna.id)
            out.tujuan= obj.tujuan
            out.active=obj.active
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
