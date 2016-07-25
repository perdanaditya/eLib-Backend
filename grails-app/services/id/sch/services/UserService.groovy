package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Pengguna
import id.sch.elib.model.User
import id.sch.elib.model.UserRole
import id.sch.elib.model.Role
import java.sql.Timestamp
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional
class UserService {

    def fetchList() {
        return User.findAll()
    }
    
    def fetchUserDetails(){
        def c = User.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                groupProperty("id","id")
                groupProperty("username","username")
                groupProperty("password","password")
                groupProperty("email","email")
                groupProperty("pengguna","pengguna")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        def renderSelected = selected.collect{
            [
                id : it.id,
                username : it.username,
                password : it.password,
                email : it.email,
                pengguna : [
                    id : it.pengguna.id,
                    noInduk : it.pengguna.noInduk,
                    nama : it.pengguna.nama,
                    jabatan : it.pengguna.jabatan,
                    kelas : it.pengguna.kelas,
                    tempatLahir : it.pengguna.tempatLahir,
                    tanggalLahir : it.pengguna.tanggalLahir,
                    jenisKelamin : it.pengguna.jenisKelamin,
                    photo : it.pengguna.photo,
                    alamat : it.pengguna.alamat
                ],
                active : it.active,
                userInput : it.userInput,
                inputTime : it.inputTime,
            ]
        }
        return renderSelected
    }
    
    boolean save(Object obj) {
        Pengguna penggunaObj = new Pengguna(
            noInduk: obj.pengguna.noInduk,
            nama: obj.pengguna.nama,
            jabatan: obj.pengguna.jabatan,
            kelas: obj.pengguna.kelas,
            tempatLahir: obj.pengguna.tempatLahir,
            tanggalLahir: obj.pengguna.tanggalLahir,
            jenisKelamin: obj.pengguna.jenisKelamin,
            photo: obj.pengguna.photo,
            alamat: obj.pengguna.alamat,
            active: obj.pengguna.active,
            userInput: obj.pengguna.userInput,
            inputTime: obj.pengguna.inputTime
        )
        if(penggunaObj.save(flush:true, failOnError: true)){
            User out = new User(
                username: obj.username,
                password: obj.password,
                email: obj.email,
                pengguna: penggunaObj,
                active: obj.active,
                userInput: obj.userInput,
                inputTime: obj.inputTime
            )

            out.inputTime = new Timestamp(new java.util.Date().getTime())
            return out.save(failOnError: true)
        }else{
            println penggunaObj.errors.allErrors
            return false
        }
        
    }
    
    boolean update(Object obj) {
        def out = User.findById(obj.id.toLong())
        def penggunaObj = Pengguna.findById(obj.pengguna.id.toLong())
        println "INI UPDATE" + obj.id + "PENGGUNA " + obj.pengguna.id
        if (out != null) {
            if(penggunaObj != null){
                Timestamp now = new Timestamp(new Date().getTime())
                penggunaObj.noInduk= obj.pengguna.noInduk
                penggunaObj.nama= obj.pengguna.nama
                penggunaObj.jabatan= obj.pengguna.jabatan
                penggunaObj.kelas= obj.pengguna.kelas
                penggunaObj.tempatLahir= obj.pengguna.tempatLahir
                def date = new Date().parse("yyyy-MM-dd HH:mm:ss", obj.pengguna.tanggalLahir);
                if(date!=null){
                    penggunaObj.tanggalLahir= date
                }else{
                    println "DATE IS NULL"
                }
                penggunaObj.jenisKelamin= obj.pengguna.jenisKelamin
                penggunaObj.photo= obj.pengguna.photo
                penggunaObj.alamat= obj.pengguna.alamat
                penggunaObj.active= obj.pengguna.active
                penggunaObj.userInput= obj.pengguna.userInput
                penggunaObj.inputTime= now
            }
            if(penggunaObj.save(flush:true, failOnError: true)){
                out.username= obj.username
                out.password= obj.password
                out.email= obj.email
                out.pengguna= penggunaObj
                out.active= obj.active
                out.userInput=obj.userInput
                out.inputTime = new Timestamp(new java.util.Date().getTime())
            }else{
                println "ERROR ON PENGGUNAOBJ"
                println penggunaObj.errors.allErrors
                return false
            }
            return out.save(failOnError: true)
        }
    }
    
    def login(Object obj){
        def user = User.findByUsernameAndPasswordAndActive(obj.username, obj.password, true)
        def renderSelected
        if (user){
            def c = User.createCriteria()
            renderSelected = c.list{
                resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                eq("id",user.id)
                projections{
                    groupProperty("id","id")
                    groupProperty("username","username")
                    groupProperty("password","password")
                    groupProperty("email","email")
                    groupProperty("pengguna","pengguna")
                    groupProperty("userRole","userRole")
                    groupProperty("active","active")
                    groupProperty("userInput","userInput")
                    groupProperty("inputTime","inputTime")
                }
                
            }
            if(renderSelected.size>0){
                renderSelected.each{
//                    println "USER ID "+it.id
                    def pId = it.id
                    def pengguna = Pengguna.createCriteria().list{
                        resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                        eq("id",pId)
                        projections{
                            property("id","id")
                            property("noInduk","noInduk")
                            property("nama","nama")
                            property("jabatan","jabatan")
                            property("kelas","kelas")
                            property("tempatLahir","tempatLahir")
                            property("tanggalLahir","tanggalLahir")
                            property("jenisKelamin","jenisKelamin")
                            property("photo","photo")
                            property("alamat","alamat")
                            property("active","active")
                        }
                    }
                    if(pengguna.size>0){
                        it.put("pengguna", pengguna.get(0))
                    }
                    def userRoleTemp = UserRole.findByUser(user)
//                    println "USER ROLE ID "+userRoleTemp.id
                    if(userRoleTemp){
                        long userRoleId=userRoleTemp.id
                        def userRole = UserRole.createCriteria().list{
                            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                            eq("id",userRoleId)
                            projections{
                                property("id","id")
                                property("role","role")
                                property("active","active")
                            }
                        }
                        userRole.each{
                            long urId = it.role.id
                            def role = Role.createCriteria().list{
                                createAlias('userRole', 'ur', CriteriaSpecification.LEFT_JOIN)
                                eq("id",urId)
                                resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                                projections{
                                    property("id","id")
                                    property("roleName","roleName")
                                    property("active","active")
                                }
                            }
                            it.put("role", role.get(0))
                        }
                        it.put("userRole", userRole.get(0))
                    }else{
                        println "userRoleTemp not found (UserServices: around line 185)"
                    }
                }
            }
            return renderSelected.get(0) 
        }
        //        return user;
    }
}
