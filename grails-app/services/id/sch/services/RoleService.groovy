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
            roleName: obj.roleName
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Role.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.roleName= obj.roleName
        }
        return out.save(failOnError: true)
    }
}
