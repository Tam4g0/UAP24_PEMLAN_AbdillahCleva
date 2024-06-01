import java.util.HashMap;
import java.util.Map;

public class User {
    private static final Map<String, User> users = new HashMap<>();
    private final String username;
    private final String password;
    private final boolean admin;
    private double saldo;
    private final Map<String, Pesanan> pesanan = new HashMap<>();

    public User(String username, String password, boolean admin, double saldo) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.saldo = saldo;
    }

    public static void addUser(String username, String password, boolean isAdmin, double saldo) {
        User user = new User(username, password, isAdmin, saldo);
        users.put(username, user);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Map<String, Pesanan> getPesanan() {
        return pesanan;
    }

    public void addPesanan(Film film, int kuantitas) {
        Pesanan newPesanan = new Pesanan(film, kuantitas);
        pesanan.put(film.getName(), newPesanan);
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}
