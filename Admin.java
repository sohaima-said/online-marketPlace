public class Admin extends User {
    private int id;
    private String password;

    public Admin(String name, String email, int id, String password) {
        super(name,email); this.id=id; this.password=password;
    }
    public int getId() { return id; }
    public String getPassword() { return password; }
}
