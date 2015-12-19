package id.sch.elib.model

import java.sql.Timestamp;

class Buku {

    String isbn
    Penerbit penerbit
    String judul
    Integer stock
    String tahunTerbit
    Byte[] cover
    RakBuku rakBuku
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [penerbit: Penerbit, rakBuku: RakBuku]
    static hasMany = [peminjaman: Peminjaman]
    static hasOne = [rating: Rating]
    
    static constraints = {
        isbn(unique:true, blank:true, nullable: false)
        penerbit(blank:true, nullable: false)
        judul(blank:true, nullable: false)
        stock(blank:true, nullable: false)
        tahunTerbit(blank:true, nullable: false)
        cover(blank:true, nullable: true, maxSize: 65535)
        rakBuku(blank:true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "idBuku"
        cover(type: "blob")
    }
}
