package id.sch.elib.model

import java.sql.Timestamp

class Pengarang {

    String namaPengarang
    Boolean active
    String userInput
    Timestamp inputTime
    
    static hasMany = [detailPengarang: DetailPengarang]
    
    static constraints = {
        namaPengarang(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    static mapping = {
        id column: "idPengarang"
    }
}
