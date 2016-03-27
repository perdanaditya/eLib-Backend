package id.sch.elib.controller

import grails.converters.JSON;
import id.sch.elib.model.Peminjaman;
import id.sch.elib.model.User
import id.sch.elib.model.Pengguna
import id.sch.elib.model.Denda
import id.sch.elib.model.DetailPeminjaman
import id.sch.elib.model.Buku
import id.sch.services.PeminjamanService;

import org.hibernate.criterion.CriteriaSpecification

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
        def id = params.id.toLong()
//        Peminjaman peminjaman = Peminjaman.findById(id.toLong())

        def c = Peminjaman.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            eq("id",id)
            projections{
                groupProperty("id","id")
                groupProperty("noPeminjaman", "noPeminjaman")
                groupProperty("user","user")
                groupProperty("tanggalPinjam","tanggalPinjam")
                //                groupProperty("detailPeminjaman","detailPeminjaman")
                groupProperty("denda","denda")
                groupProperty("totalDenda","totalDenda")
                groupProperty("active","active")
                groupProperty("userInput","userInput")
                groupProperty("inputTime","inputTime")
            }
                
        }
        if(selected.size>0){
            selected.each{
                def dId = it.id
                def denda = Denda.createCriteria().list{
                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                    eq("id",dId)
                    projections{
                        property("id","id")
                        property("nominal","nominal")
                        property("tanggal","tanggal")
                        property("active","active")
                        groupProperty("userInput","userInput")
                        groupProperty("inputTime","inputTime")
                    }
                }
                it.put("denda", denda.get(0))
                
                long peminjamanId=it.user.id
                println "USER ID "+peminjamanId
                def user = User.createCriteria().list{
                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                    eq("id",peminjamanId)
                    projections{
                        property("id","id")
                        property("username","username")
                        property("pengguna","pengguna")
                        property("email","email")
                    }
                }
                user.each{
                    long uId = it.id
                    def pengguna = Pengguna.createCriteria().list{
                        createAlias('user', 'usr', CriteriaSpecification.LEFT_JOIN)
                        eq("id",uId)
                        resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                        projections{
                            property("id","id")
                            property("noInduk","noInduk")
                            property("nama","nama")
                            property("jabatan","jabatan")
                            property("kelas","kelas")
                        }
                    }
                    it.put("pengguna", pengguna.get(0))
                }
                it.put("user", user.get(0))
                
                long detailPinj=it.id
                //                println "DETAIL PINJAM ID "+detailPinj
                def detail = DetailPeminjaman.createCriteria().list{
                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                    eq("peminjaman.id",detailPinj)
                    projections{
                        property("id","id")
                        property("buku","buku")
                        property("masaPinjam","masaPinjam")
                        property("tanggalPengembalian","tanggalPengembalian")
                        property("denda","denda")
                        property("perpanjang","perpanjang")
                        property("active","active")
                        property("userInput","userInput")
                        property("inputTime","inputTime")
                    }
                }
                detail.each{
                    long bId = it.buku.id
                    //                    println "SEARCH BUKU ID "+bId
                    //                    def buku = Buku.findAll{id == bId}
                    def buku = Buku.createCriteria().list{
                        resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                        eq("id",bId)
                        projections{
                            groupProperty("id","id")
                            groupProperty("isbn","isbn")
                            groupProperty("penerbit","penerbit")
                            groupProperty("judul","judul")
                            groupProperty("stock","stock")
                            groupProperty("tahunTerbit","tahunTerbit")
                            groupProperty("cover","cover")
                            groupProperty("rakBuku","rakBuku")
                            groupProperty("masaPinjam","masaPinjam")
                        }
                    }
                    it.put("buku", buku.get(0))
                }
                it.put("detailPeminjaman", detail)
            }
        }
        render selected as JSON
        //        render peminjaman as JSON
    }
    
    def fetchDefault(){
        def pinjam = Peminjaman.findAll()
        render pinjam as JSON
    }
}
