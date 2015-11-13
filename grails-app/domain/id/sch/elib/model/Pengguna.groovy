package id.sch.elib.model

class Pengguna {

    String noInduk
    String nama
    String jabatan
    String tempatLahir
    Date tanggalLahir
    String jenisKelamin
    String alamat
    String status
    
    static hasOne = [user: User]
    
    static constraints = {
        noInduk(blank: true, nullable: false)
        nama(blank: true, nullable: false)
        jabatan(blank: true, nullable: false)
        tempatLahir(blank: true, nullable: false)
        tanggalLahir(blank: true, nullable: false)
        jenisKelamin(blank: true, nullable: false)
        alamat(blank: true, nullable: false)
        status(blank: true, nullable: false)
    }
    
    static mapping = {
        id column: "idPengguna"
    }
}
