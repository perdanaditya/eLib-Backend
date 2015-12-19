package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.User

@Transactional
class UserService {

    def fetchList() {
        return User.findAll()
    }
    
    boolean save(Object obj) {
        User out = new User(
            username: obj.username,
            password: obj.password,
            email: obj.email,
            pengguna: obj.pengguna,
            photo: obj.photo,
            active: obj.active,
            userInput: obj.userInput,
            inputTime: obj.inputTime
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = User.findById(obj.id.toLong())
        println "INI UPDATE" + obj.id
        if (out != null) {
            out.username= obj.username
            out.password= obj.password
            out.email= obj.email
            out.pengguna= obj.pengguna
            out.photo= obj.photo
            out.active= obj.active
            out.userInput=obj.userInput
            out.inputTime=obj.inputTime
        }
        return out.save(failOnError: true)
    }
}
