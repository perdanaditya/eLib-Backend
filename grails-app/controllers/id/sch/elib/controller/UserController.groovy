package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.User;
import id.sch.services.UserService;

class UserController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def UserService

    def index() {
        render UserService.fetchList() as JSON
    }

    def userDetails(){
        render UserService.fetchUserDetails() as JSON
    }
    
    def save() {
        def output = ["message": "success"]

        if (!UserService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!UserService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        User user = User.findById(id.toLong())

        render user as JSON
    }
    
    def login(){
        //        def output = ["message": "success"]
        //
        //        if (!UserService.login(request.JSON)) {
        //            output = ["message": "failed"]
        //        }
        def output = UserService.login(request.JSON)
        if(output!=null){
            println "NOT NULL"
            render output as JSON
        }else{
            println "USER NULL, UNAUTHORIZED"
            response.status = 401;
        }
    }
}
