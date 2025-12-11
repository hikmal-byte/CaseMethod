import java.util.Arrays;
import java.util.Scanner;

public class CM2_Siakad_14 {

   static String[] matkul14 = {
      "Pendidikan Pancasila & Kewarganegaraan",
      "Dasar Pemrograman                     ",
      "Praktikum Dasar Pemrograman           ",
      "Bahasa Inggris                        ",
      "Fisika                                ",
      "Matematika Dasar                      ",
      "Konsep Teknologi Informasi            ",
      "Keselamatan dan Kesehatan Kerja       ",
      "Critical Thinking & Problem Solving   ",
   };
   static int sks14[] = {
      2, 2, 3, 2, 2, 2, 2, 2, 2
   };
   static double nilaiMahasiswa14[][];
   static String[][] dataMahasiswa14;

   static Scanner hikmal = new Scanner(System.in);
   static int jumlahMahasiswa14;
   static void jumlahMahasiswa14() {
      System.out.print("Masukkan jumlah mahasiswa: ");
      jumlahMahasiswa14 = hikmal.nextInt();
      hikmal.nextLine();
      nilaiMahasiswa14 = new double[jumlahMahasiswa14][matkul14.length];
      dataMahasiswa14 = new String[jumlahMahasiswa14][2];
      for (int i = 0; i < jumlahMahasiswa14; i++) {
         dataMahasiswa14[i][0] = null;
      }
   }

   static void menu14() {
      System.out.println("\n===================================================");
      System.out.println("\t SISTEM SIAKAD SEDERHANA(MENU)");
      System.out.println("===================================================");
      System.out.print("1. Input nilai mata kuliah\n2. Lihat KHS (konversi & IP + status)\n3. Keluar\n");
      System.out.println("===================================================");
      System.out.print("Pilih menu (1-3): ");
   }

   static void inputNilai14() {
      System.out.println("\nDaftar Mahasiswa:");
      for (int i = 0; i < jumlahMahasiswa14; i++) {
         System.out.println((i + 1) + ". " + (dataMahasiswa14[i][0] == null ? "(belum diisi) / -" : dataMahasiswa14[i][0] + " / " + dataMahasiswa14[i][1]));
      }

      System.out.print("Pilih nomnor mahasiswa (1-" + jumlahMahasiswa14 + "): ");
      int index = hikmal.nextInt() - 1;
      hikmal.nextLine();

      if (index < 0 || index >= jumlahMahasiswa14) {
         System.out.println("[ERROR] Nomor mahasiswa tidak valid!");
         return;
      }

      System.out.println("=== INPUT NILAI MAHASISWA ===");
      System.out.print("Nama : ");
      dataMahasiswa14[index][0] = hikmal.nextLine();
      System.out.print("NIM  : ");
      dataMahasiswa14[index][1] = hikmal.nextLine();

      System.out.println("\nMasukkan nilai (0-100) untuk mata kuliah berikut:");

      for (int i = 0; i < matkul14.length; i++) {
         double input = -1;
         while (input < 0 || input > 100) {
            System.out.print((i + 1) + ". " + matkul14[i] + ": ");
            input = hikmal.nextDouble();
            if (input < 0 || input > 100) {
               System.out.println("[ERROR] Nilai tidak valid! HArus 0 - 100.");
            }
         }
         nilaiMahasiswa14[index][i] = input;
      }
      System.out.println("\n[OK] Semua nilai berhasil dimasukkan.");
   }

   static void tampilKHS() {
      System.out.println("\nDaftar Mahasiswa:");
      for (int i = 0; i < jumlahMahasiswa14; i++) {
         if (dataMahasiswa14[i][0] != null) {
            System.out.println((i + 1) + ". " + dataMahasiswa14[i][0] + " / " + dataMahasiswa14[i][1]);
         } else {
            System.out.println((i + 1) + ". (Belum Diisi/Data Kosong) / -");
         }
      }

      System.out.print("Pilih nomor mahasiswa (1-" + jumlahMahasiswa14 + "): ");
      int index = hikmal.nextInt() - 1;
      hikmal.nextLine();

      if (index < 0 || index >= jumlahMahasiswa14 || dataMahasiswa14[index][0] == null) {
         System.out.println("[ERROR] Data mahasiswa belum tersedia/valid.");
         return;
      }

      System.out.println("\n==========================================");
      System.out.println("      HASIL KONVERSI NILAI MAHASISWA      ");
      System.out.println("==========================================");
      System.out.println("Nama : " + dataMahasiswa14[index][0]);
      System.out.println("NIM  : " + dataMahasiswa14[index][1]);
      System.out.println("-----------------------------------------------------------------");
      System.out.printf("%-3s %-40s %-5s %-7s %-7s %-7s\n", "No", "Mata Kuliah", "SKS", "Nilai", "Huruf", "Setara");
      System.out.println("-----------------------------------------------------------------");

      double totalSKS = 0;
      double totalBobot = 0;
      boolean adaNilaiE = false;
      boolean PancasilaJelek = false;

      for (int i = 0; i < matkul14.length; i++) {
         double nilaiAngka = nilaiMahasiswa14[index][i];
         String nilaiHuruf = getNilaiHuruf(nilaiAngka);
         double nilaiSetara = getNilaiSetara(nilaiAngka);

         System.out.printf("%-3d %-40s %-5d %-7.1f %-7s %-7.1f\n", (i + 1), matkul14[i], sks14[i], nilaiAngka, nilaiHuruf, nilaiSetara);

         totalSKS += sks14[i];
         totalBobot += (nilaiSetara * sks14[i]);

         if (nilaiHuruf.equals("E")) {
            adaNilaiE = true;
         }

         if (matkul14[i].equalsIgnoreCase("Pancasila")) {
            if (nilaiHuruf.equals("D") || nilaiHuruf.equals("E")) {
               PancasilaJelek = true;
            }
         }
      }

      System.out.println("-----------------------------------------------------------------");

      double ip = (totalSKS > 0) ? (totalBobot / totalSKS) : 0;

      String status;
      boolean isLulus = true;
      String alasanGagal = "";

      if (ip < 2.00) {
         isLulus = false;
         alasanGagal += "(Terdapat Nilai E) ";
      }
      if (adaNilaiE) {
         isLulus = false;
         alasanGagal += "(Terdapat Nilai E) ";
      }
      if (PancasilaJelek) {
         isLulus = false;
         alasanGagal += "(Nilai Pancasila < C) ";
      }

      status = isLulus ? "LULUS SEMESTER" : "TIDAK LULUS " + alasanGagal;

      System.out.println("Total SKS   : " + (int)totalSKS);
      System.out.printf("IP Semester : %.2f\n", ip);
      System.out.println("Status    : " + status);
      System.out.println("========================================");

      System.out.println("Tekan Enter untuk kembali ke menu...");
      hikmal.nextLine();
   }
   static String getNilaiHuruf(double nilai) {
      if (nilai >= 80 && nilai <= 100) return "A";
      else if (nilai > 73 && nilai <= 80) return "B+";
      else if (nilai > 65 && nilai <= 73) return "B";
      else if (nilai > 60 && nilai <= 65) return "C+";
      else if (nilai > 50 && nilai <= 60) return "C";
      else if (nilai > 39 && nilai <= 50) return "D";
      else return "E";
   }

   static double getNilaiSetara(double nilai) {
      if (nilai >= 80 && nilai <= 100) return 4.0;
      else if (nilai > 73 && nilai <= 80) return 3.5;
      else if (nilai > 65 && nilai <= 73) return 3.0;
      else if (nilai > 60 && nilai <= 65) return 2.5;
      else if (nilai > 50 && nilai <= 60) return 2.0;
      else if (nilai > 39 && nilai <= 50) return 1.0;
      else return 0.0;
   }

   public static void main(String[] args) {
      jumlahMahasiswa14();

      boolean lanjut = true;
      while (lanjut) {
         menu14();
         int pilihan = hikmal.nextInt();
         hikmal.nextLine();

         switch (pilihan) {
            case 1:
               inputNilai14();
               break;
            case 2:
               tampilKHS();
               break;
            case 3:
               System.out.println("Terima kasih, program selesai.");
               lanjut = false;
               break;
            default:
               System.out.println("Pilihan tidak valid! Silakan coba lagi.");
         }
      }
   }
}