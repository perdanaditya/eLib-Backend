package id.sch.elib.model

class RakBuku {

    String namaRak
    
    static constraints = {
        namaRak(blank: true, nullable: false)
    }
    
    static hasMany = [buku: Buku]
    static mapping = {
        id column: "idRak"
    }
}
