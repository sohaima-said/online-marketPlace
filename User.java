import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String email;

    public User(String name, String email) {
        this.userName = name; this.email = email;
    }

    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setEmail(String email) { this.email = email; }
}

