//import library yang dibutuhkan
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//Inheritance (Class Gaji merupakan child-Class dari Class Karyawan)
public class Gaji extends Karyawan {

    //constructor
    public Gaji(int id, String nama) {
        super(id, nama);
    }

    public Gaji(){
        id = 0;
        nama = "";
    }

    Scanner s = new Scanner(System.in);

    //membuat koneksi ke database mysql
    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/dbptxyz"; //url database


    //membuat objek tanggal dan menentukan formatnya kemudian disimpan pada string tanggal
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    String tanggal = ft.format(date);



                //---------------- Beberapa Method ----------------


    public void tambah() throws SQLException {
        String a = "--- Tambah Karyawan ---";
        System.out.println("\n" + a.toUpperCase() + "\n");
        try{
            idKaryawan();
            namaKaryawan();
            jabatanKaryawan();
            jamLembur();
            hariAbsen();

            System.out.println("\n\n ----- Data yang Ditambahkan -----\n\n");
            System.out.println("ID Karyawan\t\t: " + id);
            System.out.println("Nama Karyawan\t\t: " + nama);
            System.out.println("Jabatan Karyawan\t: " + jabatan);
            System.out.println("Jam Lembur Karyawan\t: " + lembur);
            System.out.println("Hari Absen Karyawan\t: " + absen + "\n\n");

            //menyimpan data pada database sql (syntax dan eksekusi)
            String sql = "INSERT INTO karyawan (tanggal, id, nama, jabatan, gajiPokok, jamLembur, hariAbsen, totalGaji) VALUES ('"+tanggal+"','"+id+"','"+nama+"','"+jabatan+"','"+gajiPokok+"','"+lembur+"','"+absen+"','"+totalGaji+"')";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);

            System.out.println("Berhasil input data!\n");

        } catch (SQLException e) {

            //tampilan error jika terjadi kesalahan pada penginputan data ke database
	        System.err.println("Terjadi kesalahan input data");
            System.err.println(e.getMessage());
            System.err.println("");

	    } catch (InputMismatchException e) {

            //tampilan error jika input data tidak sesuai dengan tipe datanya
	    	System.err.println("Inputan harus berupa angka");
            System.err.println("");
	   	}
        
    }

    public void tampilkan() throws SQLException{
        String a = "----- Data Karyawan -----";
        System.out.println("\n\n" + a.toUpperCase() + "\n"); //method toUpperCase untuk mengubah string menjadi huruf kapital

        //pemanggilan data dari database
        String sql ="SELECT * FROM karyawan"; //query untuk menampilkan data
		conn = DriverManager.getConnection(url,"root",""); //memanggil koneksi
		Statement statement = conn.createStatement(); //membuat statement untuk mengeksekusi pemanggilan dtaa
		ResultSet result = statement.executeQuery(sql); //eksekusi (menyimpan data pada object result)
		

        //menampilkan data
		while(result.next()){
            System.out.print("Tanggal Input Data\t: "); System.out.println(result.getString("tanggal"));
			System.out.print("ID Karyawan\t\t: "); System.out.println(result.getInt("id"));
            System.out.print("Nama Karyawan\t\t: "); System.out.println(result.getString("nama"));
            System.out.print("Jabatan\t\t\t: "); System.out.println(result.getString("jabatan"));
            System.out.print("Gaji Pokok\t\t: "); System.out.println(result.getInt("gajiPokok"));
            System.out.print("Jumlah Jam Lembur\t: "); System.out.println(result.getInt("jamLembur"));
            System.out.print("Jumlah Hari Absen\t: "); System.out.println(result.getInt("hariAbsen"));
            System.out.print("Total Gaji\t\t: "); System.out.println(result.getInt("totalGaji"));
            System.out.println("\n");
		}
    }

    public void ubah() throws SQLException{
        String a = "--- Update Data Karyawan ---";
        System.out.println("\n" + a.toUpperCase() + "\n");
        
        try {
            //menampilkan data
            tampilkan();
            System.out.print("\nMasukkan ID karyawan yang ingin anda update : "); int pil = s.nextInt(); //pilih data yg akan diupdate

            //menampilkan data yang dipilih
            String sql = "SELECT * FROM karyawan WHERE ID = " + pil;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            //mengubah data setelah data yang dipilih ditemukan
            if(result.next()){
                
                System.out.print("Nama baru ["+result.getString("nama")+"]\t: ");
                String nama = s.next();
                   
                //menyimpan perubahan ke database
                sql = "UPDATE karyawan SET nama='"+nama+"' WHERE ID='"+pil+"'";
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data karyawan (ID "+pil+")");
                }
            }
            statement.close(); //menutup statement database

        } catch (SQLException e) {
            //tampilan error jika terjadi kesalahan pengubahan data pada database
        	System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
            System.out.println("\n");
        }
    }
    

    public void hapus(){
        String d = "\nHapus Data Karyawan";
		System.out.println(d.toUpperCase());
		
        //exception
		try{

            //tampilkan semua data dan pilih data yang akan dihapus
	        tampilkan();
	        System.out.print("\nMasukan ID karyawan yang akan Anda Hapus : "); int pil = s.nextInt();
	        
            //hapus data pada database
	        String sql = "DELETE FROM karyawan WHERE ID = "+ pil;
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data karyawan (ID "+pil+")\n");
	        }
	   }

		catch(SQLException e){
            //tampilan error jika terjadi kesalahan pada penghapusan data
	        System.out.println("Terjadi kesalahan dalam menghapus data");
            System.err.println(e.getMessage());
            System.out.println("");
	    }
    }


}
