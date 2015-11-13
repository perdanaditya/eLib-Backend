package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Role;
import id.sch.services.RoleService;

class RoleController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def RoleService

    def index() {
        render RoleService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!RoleService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!RoleService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Role role = Role.findById(id.toLong())

        render role as JSON
    }
}
