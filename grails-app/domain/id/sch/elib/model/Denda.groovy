package id.sch.elib.model

import java.sql.Timestamp;

class Denda {

    Integer nominal
    Date tanggal
    Boolean active
    String userInput
    Timestamp inputTime
    
    static hasMany = [peminjaman: Peminjaman]
    
    static constraints = {
        nominal(blank:true, nullable: false)
        tanggal(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
}
