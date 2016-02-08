package id.sch.elib.controller

import grails.converters.JSON
import id.sch.elib.model.RakBuku;
import id.sch.services.RakBukuService;

class RakBukuController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def RakBukuService

    def index() {
        render RakBukuService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!RakBukuService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!RakBukuService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        RakBuku rak = RakBuku.findById(id.toLong())

        render rak as JSON
    }
}
