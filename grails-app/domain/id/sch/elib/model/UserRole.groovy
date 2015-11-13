package id.sch.elib.model

class UserRole implements Serializable{

    User user
    Role role
    
    static belongsTo = [user: User, role: Role]
    
    static constraints = {
        user(blank: true, nullable: false)
        role(blank: true, nullable: false)
    }
    
    static mapping = {
        id composite: ["user", "role"]
    }
}
