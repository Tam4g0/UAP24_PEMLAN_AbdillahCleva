import java.util.Scanner;

// Fitur dari Admin
public class AksiAdmin extends Aksi {
    private final Scanner scanner; 

    // Konstruktor menerima scanner dari luar
    public AksiAdmin(Scanner scanner) {
        this.scanner = scanner;
    }

    // Opsi pilihan untuk Admin
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
    }

    // Logout akun admin
    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    // Menutup aplikasi
    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    // Menampilkan list film
    @Override
    public void lihatListFilm() {
        for (Film film : Film.getFilms().values()) {
            System.out.println("Film: " + film.getName() + " - Deskripsi: " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    // Menambah list film 
    public void tambahFilm() {
        System.out.print("Nama Film: ");
        String name = scanner.next();
        scanner.nextLine(); // Mengonsumsi sisa baris
        System.out.print("Deskripsi Film: ");
        String description = scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double price = scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stock = scanner.nextInt();
        Film.addFilm(name, description, price, stock);
    }
}
