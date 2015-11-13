package id.sch.elib.model

class Role {

    String roleName
    
    static hasMany = [userRole: UserRole]
    
    static constraints = {
        roleName(blank: true, nullable: false)
    }
    
    static mapping = {
        id column: "idRole"
    }
}
