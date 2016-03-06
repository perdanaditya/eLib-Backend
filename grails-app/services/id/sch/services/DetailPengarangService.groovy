package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.DetailPengarang
import id.sch.elib.model.Pengarang
import id.sch.elib.model.Buku
import java.sql.Timestamp
import java.text.SimpleDateFormat

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class DetailPengarangService {

    def fetchList() {
        //        return DetailPengarang.findAll()

        def c = DetailPengarang.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                groupProperty("id","id")
                groupProperty("pengarang","pengarang")
                groupProperty("buku","buku")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                pengarang : [
                    id : it.pengarang.id,
                    namaPengarang : it.pengarang.namaPengarang
                ],
                buku : [
                    id : it.buku.id,
                    isbn : it.buku.isbn,
                    judul : it.buku.judul
                ],
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        DetailPengarang out;
        if(obj.newPengarang==true){
            String pengarangStr = obj.pengarang.namaPengarang
            println pengarangStr
            Pengarang pengarang1 = new Pengarang(
                namaPengarang: obj.pengarang.namaPengarang,
                active: true,
                userInput: obj.pengarang.userInput,
                inputTime: new Timestamp(new java.util.Date().getTime())
            )
            if(pengarang1.save(failOnError: true)){
                out = new DetailPengarang(
                    pengarang: pengarang1,
                    buku: obj.buku.id.toLong(),
                    active: true,
                    userInput: obj.userInput,
                    inputTime: new Timestamp(new java.util.Date().getTime())
                )
            }else{
                println pengarang1.errors.allErrors
            }
        }else{
            out = new DetailPengarang(
                pengarang: Pengarang.get(obj.pengarang.id.toLong()),
                buku: obj.buku.id.toLong(),
                active: true,
                userInput: obj.userInput,
                inputTime: new Timestamp(new java.util.Date().getTime())
            )
        }

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = DetailPengarang.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.pengarang= Pengarang.get(obj.pengarang.id.toLong())
            out.buku= Buku.get(obj.buku.id.toLong())
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
