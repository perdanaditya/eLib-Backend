
import id.sch.elib.model.Buku
import id.sch.elib.model.Peminjaman
import id.sch.elib.model.Penerbit
import id.sch.elib.model.Pengarang
import id.sch.elib.model.Pengguna
import id.sch.elib.model.RakBuku
import id.sch.elib.model.Rating
import id.sch.elib.model.Role
import id.sch.elib.model.SumberBuku
import id.sch.elib.model.User
import id.sch.elib.model.UserRole
import id.sch.elib.model.Denda
import id.sch.elib.model.DetailPeminjaman
import id.sch.elib.model.DetailPengarang
import java.sql.Timestamp
import grails.util.Holders

class BootStrap {

    def useBoostrap = false;
    //Pengguna+Penerbit
    def init = { servletContext ->
        Timestamp now = new Timestamp(new Date().getTime())
        def config = Holders.config
        this.useBoostrap = config.grails.config.useBootstrap

        if (this.useBoostrap){
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
        
            Pengguna pengguna3 = new Pengguna(
                noInduk: "1234",
                nama: "namaPengguna3",
                jabatan: "Siswa",
                kelas: "XII",
                tempatLahir: "tempatLahir3",
                tanggalLahir: now,
                jenisKelamin: "Perempuan",
                alamat: "alamat3",
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
        
            User user3 = new User(
                username: "user2",
                password: "user123*",
                email: "user@user.com",
                pengguna: pengguna3,
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
                noDdc: "001.019",
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            RakBuku rakBuku2 = new RakBuku(
                namaJenis: "Biologi",
                noDdc: "002.011",
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
                masaPinjam: 3,
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
                masaPinjam: 3,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Buku buku3 = new Buku(
                isbn: "1234",
                penerbit: penerbit2,
                judul: "judulBuku3",
                stock: 15,
                tahunTerbit: 2015,
                rakBuku: rakBuku2,
                masaPinjam: 3,
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
        
            Denda denda1 = new Denda(
                nominal: 3000,
                tanggal: now,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Peminjaman peminjaman1 = new Peminjaman(
                noPeminjaman:"noPinjam1",
                user: user2,
                tanggalPinjam: now,
                denda: denda1,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Peminjaman peminjaman2 = new Peminjaman(
                noPeminjaman: "noPinjam2",
                user: user2,
                tanggalPinjam: now,
                denda: denda1,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPeminjaman detailPeminjaman1= new DetailPeminjaman(
                peminjaman: peminjaman1,
                buku: buku1,
                masaPinjam: buku1.masaPinjam,
                perpanjang:false,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPeminjaman detailPeminjaman2= new DetailPeminjaman(
                peminjaman: peminjaman1,
                buku: buku2,
                masaPinjam: buku2.masaPinjam,
                perpanjang:false,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPeminjaman detailPeminjaman3= new DetailPeminjaman(
                peminjaman: peminjaman2,
                buku: buku1,
                masaPinjam: buku1.masaPinjam,
                perpanjang:false,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPeminjaman detailPeminjaman4= new DetailPeminjaman(
                peminjaman: peminjaman2,
                buku: buku2,
                masaPinjam: buku2.masaPinjam,
                perpanjang:false,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Pengarang pengarang1 = new Pengarang(
                namaPengarang: "namaPengarang1",
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Pengarang pengarang2 = new Pengarang(
                namaPengarang: "namaPengarang2",
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Pengarang pengarang3 = new Pengarang(
                namaPengarang: "namaPengarang3",
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            Pengarang pengarang4 = new Pengarang(
                namaPengarang: "namaPengarang4",
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPengarang detPengarang1 = new DetailPengarang(
                pengarang: pengarang1,
                buku: buku1,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPengarang detPengarang2 = new DetailPengarang(
                pengarang: pengarang2,
                buku: buku1,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            DetailPengarang detPengarang3 = new DetailPengarang(
                pengarang: pengarang1,
                buku: buku2,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            SumberBuku sumberBuku1 = new SumberBuku(
                buku: buku1,
                pengguna: pengguna1,
                namaPemberi: "",
                jumlah: 5,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        
            SumberBuku sumberBuku2 = new SumberBuku(
                buku: buku2,
                pengguna: null,
                namaPemberi: "Udin Soparudin",
                jumlah: 5,
                active: true,
                userInput: "system",
                inputTime: now
            ).save(flush:true, failOnError:true);
        }
    }
    
}
