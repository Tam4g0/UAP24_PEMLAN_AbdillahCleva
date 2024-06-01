import java.util.Scanner;

// Class Main
public class Main {
    static Scanner scanner = new Scanner(System.in); // Scanner untuk input
    static AksiAdmin aksiAdmin = new AksiAdmin(scanner); //  aksi admin dengan scanner
    static AksiUser aksiUser = new AksiUser(scanner); //  aksi user dengan scanner
    static boolean run = true; // Flag untuk menjalankan aplikasi
    static boolean isLogin = false; // Flag untuk status login

    public static void main(String[] args) {
        // Menambahkan user dan admin ke dalam sistem
        User.addUser("user", "123", false, 100000);
        User.addUser("admin", "123", true, 100000);

        // Loop utama aplikasi
        while (run) {
            Aksi.welcome(); // Menampilkan Tampilan awal
            int Aksi = scanner.nextInt();

            if (Aksi == 1) {
                while (!isLogin) {
                    // Proses login
                    System.out.println("Silakan login >_<");
                    System.out.print("Username: ");
                    String username = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    User user = authenticate(username, password);

                    if (user != null) {
                        isLogin = true;
                        Akun.login(user);
                        System.out.println("Selamat datang " + Akun.getCurrentUser().getUsername());
                        if (Akun.getCurrentUser().isAdmin()) {
                            handleAksiAdmin(); // Handle aksi admin
                        } else {
                            handleAksiUser(); // Handle aksi user
                        }
                    } else {
                        System.out.println("Username atau password salah. Silakan coba lagi.");
                    }
                }
                isLogin = false;
            } else {
                run = false; // Menghentikan aplikasi
            }
        }
    }

    // Mengautentikasi user
    private static User authenticate(String username, String password) {
        User user = User.getUsers().get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Fungsi untuk aksi user
    private static void handleAksiUser() {
        while (true) {
            aksiUser.tampilanAksi(); // Melihat opsi user
            switch (scanner.nextInt()) {
                case 1 -> aksiUser.pesanFilm();
                case 2 -> aksiUser.lihatListFilm();
                case 3 -> aksiUser.lihatPesanan();
                case 4 -> aksiUser.lihatSaldo();
                case 5 -> {
                    aksiUser.keluar();
                    return;
                }
                case 6 -> aksiUser.tutupAplikasi();
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }

    // Fungsi untuk aksi admin
    private static void handleAksiAdmin() {
        while (true) {
            aksiAdmin.tampilanAksi(); // Melihat opsi admin
            switch (scanner.nextInt()) {
                case 1 -> aksiAdmin.tambahFilm();
                case 2 -> aksiAdmin.lihatListFilm();
                case 3 -> {
                    aksiAdmin.keluar();
                    return;
                }
                case 4 -> aksiAdmin.tutupAplikasi();
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }
}
