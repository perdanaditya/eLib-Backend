package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Role
import id.sch.elib.model.User
import id.sch.elib.model.UserRole
import java.sql.Timestamp

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class UserRoleService {

    def fetchList() {
        //        return UserRole.findAll()
        def c = UserRole.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                groupProperty("id","id")
                groupProperty("user","user")
                groupProperty("role","role")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                user : [
                    id: it.user.id,
                    username: it.user.username
                ],
                role : [
                    id : it.role.id,
                    roleName: it.role.roleName
                ],
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        UserRole out = new UserRole(
            user: User.get(obj.user.id.toLong()),
            role: Role.get(obj.role.id.toLong()),
            active: obj.active,
            userInput: obj.userInput,
            inputTime: new Timestamp(new java.util.Date().getTime())
        )
        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = UserRole.findById(obj.id.toLong())
        if (out != null) {
            out.user= User.get(obj.user.id.toLong())
            out.role= Role.get(obj.role.id.toLong())
            out.active= obj.active
            out.userInput= obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        return out.save(failOnError: true)
    }
}
