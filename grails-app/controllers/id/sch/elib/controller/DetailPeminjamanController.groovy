package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.DetailPeminjaman;
import id.sch.services.DetailPeminjamanService;

class DetailPeminjamanController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def DetailPeminjamanService

    def index() {
        render DetailPeminjamanService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!DetailPeminjamanService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!DetailPeminjamanService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        DetailPeminjaman detailPeminjaman = DetailPeminjaman.findById(id.toLong())

        render detailPeminjaman as JSON
    }
}
