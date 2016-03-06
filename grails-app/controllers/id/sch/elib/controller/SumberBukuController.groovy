package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.SumberBuku;
import id.sch.services.SumberBukuService;

class SumberBukuController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def SumberBukuService

    def index() {
        render SumberBukuService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!SumberBukuService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!SumberBukuService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        SumberBuku sumberBuku = SumberBuku.findById(id.toLong())

        render sumberBuku as JSON
    }
}
