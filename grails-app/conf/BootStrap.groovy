
import id.sch.elib.model.Buku
import id.sch.elib.model.Penerbit
import id.sch.elib.model.Pengguna
import java.sql.Timestamp

class BootStrap {

    //Pengguna+Penerbit
    def init = { servletContext ->
         Timestamp now = new Timestamp(new Date().getTime())
         
        Penerbit penerbit1 = new Penerbit(
            namaPenerbit: "namaPenerbit1",
            alamatPenerbit: "alamatPenerbit1",
            kotaPenerbit: "kotaPenerbit1",
            telponPenerbit: "telponPenerbit1",
            active: true,
            userInput: "admin",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
        Penerbit penerbit2 = new Penerbit(
            namaPenerbit: "namaPenerbit2",
            alamatPenerbit: "alamatPenerbit2",
            kotaPenerbit: "kotaPenerbit2",
            telponPenerbit: "telponPenerbit2",
            active: true,
            userInput: "admin",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
//        Pengguna pengguna1 = new Pengguna(
//            noInduk: "noInduk1",
//            nama: "namaPengguna1",
//            jabatan: "pegawai",
//            tempatLahir: "tempatLahir1",
//            tanggalLahir: now,
//            jenisKelamin: "Laki - Laki",
//            alamat: "alamat1",
//            status: "aktif"
//        ).save(flush:true,failOnError:true);
//        
//        Pengguna pengguna2 = new Pengguna(
//            noInduk: "noInduk2",
//            nama: "namaPengguna2",
//            jabatan: "siswa",
//            tempatLahir: "tempatLahir2",
//            tanggalLahir: now,
//            jenisKelamin: "Laki - Laki",
//            alamat: "alamat2",
//            status: "aktif"
//        ).save(flush:true,failOnError:true);
    }
//    def destroy = {
//    }
}
