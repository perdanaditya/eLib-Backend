package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.UserRole

@Transactional
class UserRoleService {

    def fetchList() {
        return UserRole.findAll()
    }
    
    boolean save(Object obj) {
        UserRole out = new UserRole(
            user: obj.user,
            role: obj.role,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = UserRole.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.user= obj.user
            out.role= obj.role
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime= obj.inputTime
        }
        return out.save(failOnError: true)
    }
}
