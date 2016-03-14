package id.sch.elib.model

import java.sql.Timestamp

class Pengguna {

    String noInduk
    String nama
    String jabatan
    String kelas
    String tempatLahir
    Date tanggalLahir
    String jenisKelamin
    Byte[] photo
    String alamat
    Boolean active
    String userInput
    Timestamp inputTime
    
    static hasMany = [sumberBuku: SumberBuku, user: User]
//    static hasOne = [user: User]
    
    static constraints = {
        noInduk(blank: true, nullable: false)
        nama(blank: true, nullable: false)
        jabatan(blank: true, nullable: false)
        kelas (blank: true, nullable: true)
        tempatLahir(blank: true, nullable: false)
        tanggalLahir(blank: true, nullable: false)
        jenisKelamin(blank: true, nullable: false)
        photo(blank: true, nullable: true)
        alamat(blank: true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "idPengguna"
    }
}
