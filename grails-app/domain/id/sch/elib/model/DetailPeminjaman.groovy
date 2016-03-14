package id.sch.elib.model

import java.sql.Timestamp

class DetailPeminjaman {

    Peminjaman peminjaman
    Buku buku;
    Integer masaPinjam
    Date tanggalPengembalian
    BigDecimal denda
    Boolean perpanjang
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [buku: Buku, peminjaman: Peminjaman]
    
    static constraints = {
        peminjaman(blank:true, nullable: false)
        buku(blank:true, nullable: false)
        masaPinjam(blank:true, nullable:false)
        tanggalPengembalian(blank:true, nullable: true)
        denda(blank:true, nullable: true)
        perpanjang (blank:true, nullable:false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
}
