package id.sch.util

import grails.converters.deep.JSON
import grails.transaction.Transactional

@Transactional
class UtilService {

    static JSON toJson(def obj) {
        return obj as JSON
    }
    
    static JSON toDeepJson(def obj) {
        JSON.use("deep") {
            return obj as JSON
        }
    }
}
