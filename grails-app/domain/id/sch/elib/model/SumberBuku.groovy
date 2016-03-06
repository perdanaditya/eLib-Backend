package id.sch.elib.model

import java.sql.Timestamp

class SumberBuku {

    Buku buku
    Pengguna pengguna
    String namaPemberi
    Integer jumlah
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [buku: Buku, pengguna: Pengguna]
    
    static constraints = {
        buku(blank:true, nullable: false)
        pengguna(blank:true, nullable: true)
        namaPemberi(blank:true, nullable: true)
        jumlah(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    static mapping = {
        id column: "idSumber"
    }
}
