import java.util.Scanner;
public class BeasiswaArray {
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;
    static String[] nama = new String[MAX];
    static String[] nim = new String[MAX];
    static double[] ipk = new double[MAX];
    static String[] jenis = new String[MAX];
    static int[] penghasilan = new int[MAX];

    static int jumlahData = 0;

    static void tambahData() {
        if (jumlahData >= MAX) {
            System.out.println("Data penuh! Tidak dapat menambah pendaftar lagi.");
            return;
        }

        System.out.print("Nama Mahasiswa: ");
        nama[jumlahData] = sc.nextLine();

        System.out.print("NIM: ");
        nim[jumlahData] = sc.nextLine();

        System.out.print("IPK terakhir: ");
        ipk[jumlahData] = Double.parseDouble(sc.nextLine());

        System.out.print("Jenis Beasiswa (Reguler/Unggulan/Riset): ");
        jenis[jumlahData] = sc.nextLine();

        if (!jenis[jumlahData].equalsIgnoreCase("Reguler") &&
            !jenis[jumlahData].equalsIgnoreCase("Unggulan") &&
            !jenis[jumlahData].equalsIgnoreCase("Riset")) {
                System.out.println("Jenis beasiswa tidak valid!");
                return;
        }

        System.out.print("Penghasilan orang tua (maksimal 2000000): ");
        penghasilan[jumlahData] = Integer.parseInt(sc.nextLine());

        if (penghasilan[jumlahData] > 2000000) {
            System.out.println("Pendaftaran dibatalkan karena penghasilan melebihi batas.");
            return;
        }

        jumlahData++;
        System.out.println("Pendaftar berhasil disimpan. Total pendaftar: " + jumlahData);
    } 

    static void tampilkanSemua() {
        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Nama\t\tNIM\t\tIPK\tJenis\t\tPenghasilan");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%s\t\t%s\t\t%.2f\t%s\t\t%d\n",
                nama[i], nim[i], ipk[i], jenis[i], penghasilan[i]);
        }
    }


    // test//