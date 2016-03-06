package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.DetailPengarang;
import id.sch.services.DetailPengarangService;

class DetailPengarangController {
    
    static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def DetailPengarangService

    def index() {
        render DetailPengarangService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!DetailPengarangService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!DetailPengarangService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        DetailPengarang detailPengarang = DetailPengarang.findById(id.toLong())

        render detailPengarang as JSON
    }
}
