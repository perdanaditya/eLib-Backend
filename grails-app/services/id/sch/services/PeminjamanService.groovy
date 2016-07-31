package id.sch.services

import grails.converters.JSON
import grails.transaction.Transactional
import id.sch.elib.model.Peminjaman
import id.sch.elib.model.User
import id.sch.elib.model.Pengguna
import id.sch.elib.model.Denda
import id.sch.elib.model.DetailPeminjaman
import id.sch.elib.model.Buku
import java.sql.Timestamp
import java.util.Calendar
import java.text.SimpleDateFormat;

import org.hibernate.criterion.CriteriaSpecification

@Transactional
class PeminjamanService {

    def fetchList() {
        def c = Peminjaman.createCriteria()
        def selected = c.list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
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
                //                def dId = it.id
                //                def denda = Denda.createCriteria().list{
                //                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                //                    eq("id",dId)
                //                    projections{
                //                        property("id","id")
                //                        property("nominal","nominal")
                //                        property("tanggal","tanggal")
                //                        property("active","active")
                //                        groupProperty("userInput","userInput")
                //                        groupProperty("inputTime","inputTime")
                //                    }
                //                }
                //                it.put("denda", denda.get(0))
                
                long peminjamanId=it.user.id
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
        return selected
    }
    
    String generateNoPinjam(){
        def peminjamList=Peminjaman.findAll()
        String nomor="";
        Timestamp now = new Timestamp(new java.util.Date().getTime())
        Calendar thisDate = Calendar.getInstance();
        Calendar thoseDate = Calendar.getInstance();
        thisDate.setTime(now);
        int index=1;
        peminjamList.each{pinjam ->
            thoseDate.setTime(pinjam.tanggalPinjam);
            if (thisDate.get(Calendar.YEAR) == thoseDate.get(Calendar.YEAR)
                && thisDate.get(Calendar.MONTH) == thoseDate.get(Calendar.MONTH)) {
                index=index+1;
            }
        }
        nomor=(thisDate.get(Calendar.MONTH) + 1) + "/" + thisDate.get(Calendar.YEAR)+ "/"+String.format("%03d", index)
        return nomor;
    }
    
    boolean save(Object obj) {
        String nomor=generateNoPinjam()
        def dendaList = Denda.findAll("from Denda order by tanggal asc")
        def singleDenda=dendaList.get(0)//perbaiki lagi cara pengambilan denda
        if(singleDenda!=null){
            Peminjaman out = new Peminjaman(
                noPeminjaman: nomor,
                user: obj.user.id.toLong(),
                tanggalPinjam: new Timestamp(new java.util.Date().getTime()),
                denda: singleDenda.id.toLong(),
                active: true,
                userInput: obj.userInput,
                inputTime: new Timestamp(new java.util.Date().getTime())
            )
            if(out.save(failOnError: true)){
                def detailPinjamList=obj.detailPeminjaman
                detailPinjamList.each{dp->
                    def bukuPinjam = Buku.get(dp.buku.id.toLong())
                    DetailPeminjaman detail= new DetailPeminjaman(
                        peminjaman: out,
                        buku: bukuPinjam,
                        masaPinjam: bukuPinjam.masaPinjam,
                        perpanjang:false,
                        active: true,
                        userInput: obj.userInput,
                        inputTime: new Timestamp(new java.util.Date().getTime())
                    )
                    if(detail.save(flush:true, failOnError:true)){
                        return true
                    }else{
                        println "============ DETAIL PEMINJAMAN ERROR ============"
                        println detail.errors.allErrors
                        return false
                    }
                }
            }else{
                println "============ PEMINJAMAN ERROR ============"
                println out.errors.allErrors
                return false
            }
        }else{
            println "DENDA IS NULL"
            return false
        }
    }
    
    boolean update(Object obj) {
        def out = Peminjaman.findById(obj.id.toLong())
        //delete old data
        out.detailPeminjaman.collect().each{
            out.removeFromDetailPeminjaman(it)
            it.delete()
        }
        //update data peminjaman
        if(obj.noPeminjaman != null){
            out.noPeminjaman= obj.noPeminjaman
        }
        if(obj.user && obj.user.id){
            out.user= User.get(obj.user.id.toLong())
        }
        if(obj.tanggalPinjam){
            out.tanggalPinjam= Timestamp.valueOf(obj.tanggalPinjam)
        }
        if(obj.denda && obj.denda.id){
            out.denda= Denda.get(obj.denda.id.toLong())
        }
        if(obj.totalDenda){
            out.totalDenda= obj.totalDenda
        }
        if(obj.active != null){
            out.active=obj.active
        }
        if (obj != null) {
            out.userInput=obj.userInput
            out.inputTime = new Timestamp(new java.util.Date().getTime())
        }
        if(out.save(failOnError: true)){
            //re-save new data
            def detail =obj.detailPeminjaman
            detail.each{ dp ->
                DetailPeminjaman dPeminjaman= new DetailPeminjaman()
                if(out != null){
                    dPeminjaman.peminjaman=out
                }
                if(dp.buku != null){
                    dPeminjaman.buku=Buku.get(dp.buku.id.toLong())
                }
                if(dp.masaPinjam != null){
                    dPeminjaman.masaPinjam=dp.masaPinjam
                }
                if(dp.tanggalPengembalian != null){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                    Date date= df.parse(dp.tanggalPengembalian)
                    dPeminjaman.tanggalPengembalian=date
                }
                if(dp.denda != null){
                    dPeminjaman.denda=dp.denda
                }
                if(dp.perpanjang != null){
                    dPeminjaman.perpanjang=dp.perpanjang
                }
                if(dp.active != null){
                    dPeminjaman.active=dp.active
                }
                if(dp.userInput != null){
                    dPeminjaman.userInput=dp.userInput
                }
                if(dPeminjaman != null){
                    dPeminjaman.inputTime=new Timestamp(new java.util.Date().getTime())
                }
                if(dPeminjaman.save(failOnError: true)){
                        
                }else{
                    println "============ DETAIL PEMINJAMAN ERROR ============"
                    println dPeminjaman.errors.allErrors
                    return false
                }
            }
        }else{
            println "============ PEMINJAMAN ERROR ============"
            println out.errors.allErrors
            return false
        }
    }
}
