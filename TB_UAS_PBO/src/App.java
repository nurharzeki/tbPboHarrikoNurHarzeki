//import library
import java.sql.*;
import java.util.*;

public class App {

    //koneksi
    static Connection conn;

    //driver class
    public static void main(String[] args) throws Exception {

        try {
            String url = "jdbc:mysql://localhost:3306/dbptxyz";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","");

            Scanner s = new Scanner(System.in);
            
            //objek dari kelas gaji
            Gaji karyawan1 = new Gaji();

            boolean lanjutkan = true;
            while(lanjutkan){
                System.out.println("\n===============   PROGRAM PENGHITUNG GAJI KARYAWAN PADA PT XYZ   ===============\n");
                System.out.println("1. Lihat data karyawan");
                System.out.println("2. Tambah karyawan");
                System.out.println("3. Ubah data karyawan");
                System.out.println("4. Hapus karyawan");
                System.out.println("5. Keluar\n");
                System.out.print("Masukkan pilihan anda sesuai nomor diatas : "); int pil = s.nextInt();

                switch(pil){
                    case 1 : karyawan1.tampilkan(); break;
                    case 2 : karyawan1.tambah(); break;
                    case 3 : karyawan1.ubah(); break;
                    case 4 : karyawan1.hapus(); break;
                    case 5 : lanjutkan = false; break;
                    default : System.out.println("Pilihan menu tidak ada! Silahkan masukkan pilihan yang tersedia!\n");
                }
            }
            s.close();
        } catch(ClassNotFoundException e) {
            //tampilan error jika driver koneksi database tidak ditemukan
            System.err.println("Driver Error");
            System.err.println(e.getMessage());
            System.exit(0);

        } catch(SQLException e){
            //tampilan error jika koneksi gagal
            System.err.println("Tidak berhasil koneksi");
            System.err.println(e.getMessage());
            System.err.println("");
        }
    }
}
