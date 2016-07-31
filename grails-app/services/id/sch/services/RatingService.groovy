package id.sch.services

import grails.transaction.Transactional
import id.sch.elib.model.Rating

@Transactional
class RatingService {

    def fetchList() {
        return Rating.findAll()
    }
    
    boolean save(Object obj) {
        Rating out = new Rating(
            buku: obj.buku,
            likeCount: obj.likeCount,
            dislike: obj.dislike
        )

        return out.save(failOnError: true)
    }
    
    boolean update(Object obj) {
        def out = Rating.findById(obj.id.toLong())
        if (out != null) {
            out.buku= obj.buku
            out.likeCount= obj.likeCount
            out.dislike= obj.dislike
        }
        return out.save(failOnError: true)
    }
}
