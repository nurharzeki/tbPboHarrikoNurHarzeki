//import library yg dibutuhkan
import java.util.*;

//Implements (Class Karyawan mengimplementasikan Interface PTXYZ, sekaligus menjadi mother-Class dari Class Gaji)
public class Karyawan implements PTXYZ{
    
    //atribut
    public int id;
    public String nama;
    public String jabatan;
    public int gajiPokok;
    public int lembur;
    public int absen;
    public int totalGaji;


    //Constructor
    public Karyawan(int id, String nama){
        this.id = id;
        this.nama = nama;
    }

    public Karyawan(){
        id = 0;
        nama = "";
    }

    //Scanner
    Scanner s = new Scanner(System.in);



                            //---------------- Beberapa Method -----------------------

    @Override
    public void idKaryawan() {
        System.out.print("Masukkan ID karyawan\t\t: ");    
        id = s.nextInt();
    }

    @Override
    public void namaKaryawan() {
        System.out.print("Masukkan nama karyawan\t\t: ");
        nama = s.next();
    }

    @Override
    public void jabatanKaryawan() {

        //perulangan while
        boolean x = false;
        while(x == false){
            System.out.println("\nMasukkan jabatan karyawan sesuai pilihan yang tersedia");
            System.out.println("1. Direktur");
            System.out.println("2. Manager");
            System.out.println("3. Kepala Divisi");
            System.out.println("4. Staff Divisi");
            System.out.println("5. Lainnya\n");
            System.out.print("Jabatan (Masukkan sesuai nomor diatas)\t: "); int pilihan = s.nextInt();

            //percabangan switch
            switch(pilihan){
                case 1 : {jabatan = "Direktur"; gajiPokok = 30000000; x=true;} break;
                case 2 : {jabatan = "Manager"; gajiPokok = 20000000; x=true;} break;
                case 3 : {jabatan = "Kepala Divisi"; gajiPokok = 10000000; x=true;} break;
                case 4 : {jabatan = "Staff Divisi"; gajiPokok = 5000000; x=true;} break;
                case 5 : {jabatan = "Lainnya"; gajiPokok = 2500000; x=true;} break;
                default : System.err.println("Pilihan Tidak Ada!"); break;
            }
        }
    }

    @Override
    public void jamLembur() {
        System.out.print("Masukkan total jam lembur karyawan (hanya dalam bentuk angka (bilangan)) : ");
        lembur = s.nextInt();

        //perhitungan matematika
        totalGaji = gajiPokok + (lembur * 50000);
    }

    @Override
    public void hariAbsen() {
        System.out.print("Masukkan total hari absen karyawan (hanya dalam bentuk angka (bilangan)) : ");
        absen = s.nextInt();

        //perhitungan matematika
        totalGaji = totalGaji - (absen * 100000);
    }
   
}
