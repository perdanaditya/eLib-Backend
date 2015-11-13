package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Penerbit;
import id.sch.services.PenerbitService;

class PenerbitController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def PenerbitService

    def index() {
        render PenerbitService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!PenerbitService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!PenerbitService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Penerbit penerbit = Penerbit.findById(id.toLong())

        render penerbit as JSON
    }
}
