package id.sch.elib.model

import java.sql.Timestamp

class Peminjaman implements Serializable{

    String noPeminjaman;
    User user;
    Date tanggalPinjam
    Denda denda
    BigDecimal totalDenda
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [user:User, denda: Denda]
    static hasMany = [detailPeminjaman: DetailPeminjaman]
    
    static constraints = {
        noPeminjaman(unique:true, blank: true, nullable: false)
        user(blank: true, nullable: false)
        tanggalPinjam(blank: true, nullable: false)
        denda(blank:true, nullable: false)
        totalDenda(blank: true, nullable: true)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "peminjamanId"
    }
}
