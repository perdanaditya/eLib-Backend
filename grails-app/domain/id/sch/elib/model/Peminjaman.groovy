package id.sch.elib.model

import java.sql.Timestamp

class Peminjaman implements Serializable{

    Buku buku;
    User user;
    Date tanggalPinjam
    Integer masaPinjam
    Date tanggalPengembalian
    Denda denda
    Integer totalDenda
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [user:User, peminjaman: Peminjaman]
    static hasMany = [buku: Buku]
    
    static constraints = {
        buku(blank: true, nullable: false)
        user(blank: true, nullable: false)
        tanggalPinjam(blank: true, nullable: false)
        masaPinjam(blank: true, nullable: false)
        tanggalPengembalian(blank: true, nullable: true)
        denda(blank:true, nullable: false)
        totalDenda(blank: true, nullable: true)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "userId"
    }
}
