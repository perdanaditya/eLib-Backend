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
}
