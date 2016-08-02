package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Pengunjung;
import id.sch.services.PengunjungService;

class PengunjungController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def PengunjungService

    def index() {
        render PengunjungService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!PengunjungService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!PengunjungService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Pengunjung pengunjung = Pengunjung.findById(id.toLong())

        render pengunjung as JSON
    }
}
