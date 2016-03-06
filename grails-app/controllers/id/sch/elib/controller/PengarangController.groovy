package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Pengarang;
import id.sch.services.PengarangService;

class PengarangController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def PengarangService

    def index() {
        render PengarangService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!PengarangService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!PengarangService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Pengarang pengarang = Pengarang.findById(id.toLong())

        render pengarang as JSON
    }
}
