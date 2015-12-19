package id.sch.elib.model

import java.sql.Timestamp

class RakBuku {

    String namaRak
    Boolean active
    String userInput
    Timestamp inputTime
    
    static constraints = {
        namaRak(blank: true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static hasMany = [buku: Buku]
    static mapping = {
        id column: "idRak"
    }
}
