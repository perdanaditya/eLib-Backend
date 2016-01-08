package id.sch.elib.model

class Rating {

    Buku buku
    Integer likeCount
    Integer dislike
    
    static belongsTo = [buku: Buku]
    
    static constraints = {
        buku(blank:true, nullable: false)
        likeCount(blank:true, nullable: false)
        dislike(blank:true, nullable: false)
    }
    
    static mapping = {
        id column: "idRating"
    }
}
