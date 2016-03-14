package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Peminjaman;
import id.sch.services.PeminjamanService;

class PeminjamanController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def PeminjamanService

    def index() {
        render PeminjamanService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!PeminjamanService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!PeminjamanService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Peminjaman peminjaman = Peminjaman.findById(id.toLong())

        render peminjaman as JSON
    }
    
    def fetchDefault(){
        def pinjam = Peminjaman.findAll()
        render pinjam as JSON
    }
}
