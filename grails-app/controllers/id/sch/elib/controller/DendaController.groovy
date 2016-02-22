package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Denda;
import id.sch.services.DendaService;

class DendaController {
    
    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def DendaService

    def index() {
        render DendaService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!DendaService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!DendaService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Denda denda = Denda.findById(id.toLong())

        render denda as JSON
    }
}
