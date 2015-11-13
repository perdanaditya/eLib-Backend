package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.UserRole;
import id.sch.services.UserRoleService;

class UserRoleController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def UserRoleService

    def index() {
        render UserRoleService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!UserRoleService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!UserRoleService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        UserRole userRole = UserRole.findById(id.toLong())

        render userRole as JSON
    }
}
