import java.io.*;
import java.util.*;

public class ProductStore {
    private static List<Product> products = new ArrayList<>();

    public static void addProduct(Product p) {
        products.add(p); }
    public static List<Product> getAllProducts() { return products; }

    public static void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("store.dat"))) {
            oos.writeObject(products);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public static void loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("store.dat"))) {
            products = (List<Product>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No product file found, starting empty store.");
            products = new ArrayList<>();
        }
    }
}
