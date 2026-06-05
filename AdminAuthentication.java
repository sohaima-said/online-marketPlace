import java.io.*;
import java.util.*;


public class AdminAuthentication implements Serializable {
    private ArrayList<Admin> admins = new ArrayList<>();

    public AdminAuthentication() { loadAdmins(); }

    public boolean register(String name, String email, int id, String password) {
        if (getAdmin(id) != null) return false;
        admins.add(new Admin(name,email,id,password));
        saveAdmins();
        return true;
    }

    public boolean login(int id, String password) {
        Admin a = getAdmin(id);
        return a != null && a.getPassword().equals(password);
    }

    public Admin getAdmin(int id) {
        for (Admin a : admins) if (a.getId() == id) return a;
        return null;
    }

    public ArrayList<Admin> getAllAdmins() { return admins; }

    public void saveAdmins() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("admins.dat"))){
            oos.writeObject(admins);
        } catch(Exception e){ e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public void loadAdmins() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("admins.dat"))){
            admins = (ArrayList<Admin>) ois.readObject();
        } catch(Exception ignored){}
    }
}
