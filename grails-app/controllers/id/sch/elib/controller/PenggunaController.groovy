package id.sch.elib.controller

import grails.converters.JSON
import id.sch.elib.model.Pengguna
import id.sch.services.PenggunaService;

class PenggunaController {

    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def PenggunaService

    def index() {
        render PenggunaService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!PenggunaService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!PenggunaService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Pengguna pengguna = Pengguna.findById(id.toLong())

        render pengguna as JSON
    }
}
