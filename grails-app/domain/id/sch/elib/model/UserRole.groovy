package id.sch.elib.model

import java.sql.Timestamp

class UserRole implements Serializable{

    User user
    Role role
    Boolean active
    String userInput
    Timestamp inputTime
    
    static belongsTo = [user: User, role: Role]
    
    static constraints = {
        user(blank: true, nullable: false)
        role(blank: true, nullable: false)
        active(blank:true, nullable: false)
        userInput(blank:true, nullable: false)
        inputTime(blank:true, nullable: false)
    }
    
    static mapping = {
        id composite: ["user", "role"]
    }
}
