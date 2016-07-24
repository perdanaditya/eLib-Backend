package id.sch.elib.model

import java.sql.Timestamp

class User {

    String username
    String password
    String email
//    Pengguna pengguna
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [pengguna: Pengguna]
//    static hasOne = [pengguna: Pengguna]
    static hasMany = [userRole: UserRole, peminjaman: Peminjaman, pengunjung: Pengunjung]
    
    static constraints = {
        username(uniqe: true, blank: true, nullable: false)
        password(blank: true, nullable: false)
        email(blank: true, nullable: false)
        pengguna(unique:true, blank: true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "userId"
    }
}