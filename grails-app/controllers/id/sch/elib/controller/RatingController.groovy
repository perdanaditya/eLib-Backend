package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Rating;
import id.sch.services.RatingService;

class RatingController {

   static allowedMethods = [index: "GET", save: "POST", update: "PUT", delete: ["DELETE"], search: "POST", show: "GET"]
    def RatingService

    def index() {
        render RatingService.fetchList() as JSON
    }

    def save() {
        def output = ["message": "success"]

        if (!RatingService.save(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def update() {
        def output = ["message": "success"]

        if (!RatingService.update(request.JSON)) {
            output = ["message": "failed"]
        }

        render output as JSON
    }

    def show() {
        def id = params.id
        Rating rating = Rating.findById(id.toLong())

        render rating as JSON
    }
}
