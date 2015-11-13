package id.sch.elib.model

class Penerbit {

    
    String namaPenerbit
    String alamatPenerbit
    String kotaPenerbit
    String telponPenerbit
    
    static hasMany = [buku: Buku]
    
    static constraints = {
        namaPenerbit(blank:true, nullable: false)
        alamatPenerbit(blank:true, nullable: false)
        kotaPenerbit(blank:true, nullable: false)
        telponPenerbit(blank:true, nullable: false)
    }
    
    static mapping ={
        id column: "idPenerbit"
    }
}
