import java.io.*;
import java.util.*;

public class Authentication implements Serializable {
    private ArrayList<Customer> users = new ArrayList<>();

    public Authentication() { loadUsers(); }

    public boolean register(String name, String email) {
        if (getCustomer(name) != null) return false;
        users.add(new Customer(name,email));
        saveUsers();
        return true;
    }

    public boolean login(String name, String email) {
        Customer c = getCustomer(name);
        return c != null && c.getEmail().equals(email);
    }

    public Customer getCustomer(String name) {
        for (Customer c : users)
            if (c.getUserName().equalsIgnoreCase(name)) return c;
        return null;
    }

    public void saveUsers() {
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("users.dat"))){
            oos.writeObject(users);
        } catch(Exception ignored){}
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Customer> loadUsers() {
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("users.dat"))){
            users=(ArrayList<Customer>)ois.readObject();
        } catch(Exception ignored){}
        return users;
    }
}
