package id.sch.elib.model

import java.sql.Timestamp

class Penerbit {

    
    String namaPenerbit
    String alamatPenerbit
    String kotaPenerbit
    String telponPenerbit
    Boolean active
    String userInput
    Timestamp inputTime
    
//    static hasMany = [buku: Buku]
    
    static constraints = {
        namaPenerbit(blank:true, nullable: false)
        alamatPenerbit(blank:true, nullable: false)
        kotaPenerbit(blank:true, nullable: false)
        telponPenerbit(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping ={
        id column: "idPenerbit"
    }
}
