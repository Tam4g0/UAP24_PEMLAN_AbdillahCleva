import java.util.Scanner;

// Fitur User
public class AksiUser extends Aksi {
    private final Scanner scanner; 

    // Konstruktor menerima scanner dari luar
    public AksiUser(Scanner scanner) {
        this.scanner = scanner;
    }

    // Opsi untuk User
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Lihat Pesanan");
        System.out.println("4. Lihat Saldo");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    // Logout akun user
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

    // Menampilkan daftar film
    @Override
    public void lihatListFilm() {
        for (Film film : Film.getFilms().values()) {
            System.out.println("Film: " + film.getName() + " - Deskripsi: " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    // Menegecek saldo user saat ini
    public void lihatSaldo() {
        User currentUser = Akun.getCurrentUser();
        System.out.println("Saldo anda: " + currentUser.getSaldo());
    }

    // Memesan tiket untuk film bioskop
    public void pesanFilm() {
        System.out.print("Nama Film yang ingin dipesan: ");
        String filmName = scanner.next();
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlah = scanner.nextInt();

        Film film = Film.getFilms().get(filmName);
        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        User currentUser = Akun.getCurrentUser();
        double totalHarga = film.getPrice() * jumlah;

        if (film.getStock() < jumlah) {
            System.out.println("Stok tiket tidak mencukupi.");
        } else if (currentUser.getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + currentUser.getSaldo());
        } else {
            currentUser.setSaldo(currentUser.getSaldo() - totalHarga);
            film.setStock(film.getStock() - jumlah);
            currentUser.addPesanan(film, jumlah);
            System.out.println("Tiket berhasil dipesan.");
        }
    }

    // Melihat pesanan yang telah dipesan
    public void lihatPesanan() {
        User currentUser = Akun.getCurrentUser();
        if (currentUser.getPesanan().isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            for (Pesanan pesanan : currentUser.getPesanan().values()) {
                System.out.println("Film: " + pesanan.getFilm().getName() + " - Jumlah: " + pesanan.getKuantitas() + " - Total Harga: " + pesanan.getFilm().getPrice() * pesanan.getKuantitas());
            }
        }
    }
}
