package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Buku;
import id.sch.services.BukuService;

class BukuController {

   static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def BukuService

    def index() {
        render BukuService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!BukuService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!BukuService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Buku buku = Buku.findById(id.toLong())

        render buku as JSON
    }
}
