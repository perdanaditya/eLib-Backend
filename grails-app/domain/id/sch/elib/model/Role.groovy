package id.sch.elib.model

import java.sql.Timestamp

class Role {

    String roleName
    Boolean active
    String userInput
    Timestamp inputTime
    
    static hasMany = [userRole: UserRole]
    
    static constraints = {
        roleName(unique:true, blank: true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "idRole"
    }
}
