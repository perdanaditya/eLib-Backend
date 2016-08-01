package id.sch.elib.model

import com.sun.jmx.snmp.Timestamp

class Pengunjung {

//    User user
    String tujuan
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [pengguna: Pengguna]
    
    static constraints = {
    }
    static mapping = {
        id column: "idPengunjung"
    }
}
