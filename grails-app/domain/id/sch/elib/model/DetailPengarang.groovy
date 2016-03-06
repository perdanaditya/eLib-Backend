package id.sch.elib.model

import java.sql.Timestamp

class DetailPengarang {

    Pengarang pengarang
    Buku buku
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [buku: Buku, pengarang: Pengarang]
    
    static constraints = {
        pengarang(blank:true, nullable: false)
        buku(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    static mapping = {
        id column: "idDetailPengarang"
    }
}
