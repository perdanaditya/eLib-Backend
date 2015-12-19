package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Role

@Transactional
class RoleService {

    def fetchList() {
        return Role.findAll()
    }
    
    boolean save(Object obj) {
        Role out = new Role(
            roleName: obj.roleName,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Role.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.roleName= obj.roleName
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime=obj.inputTime
        }
        return out.save(failOnError: true)
    }
}
