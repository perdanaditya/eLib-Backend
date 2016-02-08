
import id.sch.elib.model.Buku
import id.sch.elib.model.Peminjaman
import id.sch.elib.model.Penerbit
import id.sch.elib.model.Pengguna
import id.sch.elib.model.RakBuku
import id.sch.elib.model.Rating
import id.sch.elib.model.Role
import id.sch.elib.model.User
import id.sch.elib.model.UserRole
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
            userInput: "system",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
        Penerbit penerbit2 = new Penerbit(
            namaPenerbit: "namaPenerbit2",
            alamatPenerbit: "alamatPenerbit2",
            kotaPenerbit: "kotaPenerbit2",
            telponPenerbit: "telponPenerbit2",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
        Pengguna pengguna1 = new Pengguna(
            noInduk: "noInduk1",
            nama: "namaPengguna1",
            jabatan: "Pegawai",
            kelas: null,
            tempatLahir: "tempatLahir1",
            tanggalLahir: now,
            jenisKelamin: "Laki - Laki",
            alamat: "alamat1",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
        Pengguna pengguna2 = new Pengguna(
            noInduk: "noInduk2",
            nama: "namaPengguna2",
            jabatan: "Siswa",
            kelas: "XI",
            tempatLahir: "tempatLahir2",
            tanggalLahir: now,
            jenisKelamin: "Laki - Laki",
            alamat: "alamat2",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true,failOnError:true);
        
        User user1 = new User(
            username: "admin",
            password: "admin123*",
            email: "admin@admin.com",
            pengguna: pengguna1,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        User user2 = new User(
            username: "user1",
            password: "user123*",
            email: "user@user.com",
            pengguna: pengguna2,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Role role1 = new Role(
            roleName: "ADMIN_ROLE",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Role role2 = new Role(
            roleName: "USER_ROLE",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        UserRole userRole1 = new UserRole(
            user: user1,
            role: role1,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        UserRole userRole2 = new UserRole(
            user: user2,
            role: role2,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        RakBuku rakBuku1 = new RakBuku(
            namaJenis: "Ekonomi",
            noDdc: "002.019",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        RakBuku rakBuku2 = new RakBuku(
            namaJenis: "Biologi",
            noDdc: "003.011",
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Buku buku1 = new Buku(
            isbn: "ISBN 978-602-1237-65-6",
            penerbit: penerbit1,
            judul: "judulBuku1",
            stock: 15,
            tahunTerbit: 2015,
            rakBuku: rakBuku1,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Buku buku2 = new Buku(
            isbn: "ISBN 978-602-1237-65-7",
            penerbit: penerbit2,
            judul: "judulBuku2",
            stock: 15,
            tahunTerbit: 2015,
            rakBuku: rakBuku2,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Rating rating1 = new Rating(
            buku: buku1,
            likeCount: 14,
            dislike: 3
        ).save(flush:true, failOnError:true);
        
        Rating rating2 = new Rating(
            buku: buku2,
            likeCount: 10,
            dislike: 35
        ).save(flush:true, failOnError:true);
        
        Peminjaman peminjaman1 = new Peminjaman(
            buku: buku1,
            user: user2,
            tanggalPinjam: now,
            masaPinjam: 3,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
        
        Peminjaman peminjaman2 = new Peminjaman(
            buku: buku2,
            user: user2,
            tanggalPinjam: now,
            masaPinjam: 3,
            active: true,
            userInput: "system",
            inputTime: now
        ).save(flush:true, failOnError:true);
    }
//    def destroy = {
//    }
}
