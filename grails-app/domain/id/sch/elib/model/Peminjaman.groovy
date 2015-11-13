package id.sch.elib.model

class Peminjaman implements Serializable{

    Buku buku;
    User user;
    Date tanggalPinjam
    Integer masaPinjam
    Date tanggalPengembalian
    Integer totalDenda
    
    static belongsTo = [buku: Buku, user:User]
    
    static constraints = {
        buku(blank: true, nullable: false)
        user(blank: true, nullable: false)
        tanggalPinjam(blank: true, nullable: false)
        masaPinjam(blank: true, nullable: false)
        tanggalPengembalian(blank: true, nullable: true)
        totalDenda(blank: true, nullable: false)
    }
    
    static mapping = {
        id composite: ["buku", "user"]
    }
}
