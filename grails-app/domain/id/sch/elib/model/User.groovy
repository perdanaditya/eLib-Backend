package id.sch.elib.model

class User {

    String username
    String password
    String email
    Pengguna pengguna
    Byte[] photo
    String status
    
    static belongsTo = [pengguna: Pengguna]
    static hasMany = [peminjaman: Peminjaman, userRole: UserRole]
    
    static constraints = {
        username(uniqe: true, blank: true, nullable: false)
        password(blank: true, nullable: false)
        email(blank: true, nullable: false)
        pengguna(blank: true, nullable: false)
        photo(blank: true, nullable: true)
        status(blank: true, nullable: false)
    }
    
    static mapping = {
        id column: "userId"
    }
}