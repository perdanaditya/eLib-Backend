package id.sch.elib.model

import java.sql.Timestamp

class Pengunjung {

    String tujuan
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [pengguna: Pengguna]
    
    static constraints = {
        
    }
    static mapping = {
        id column: "idPengunjung"
        sort inputTime: "asc"
    }
}
