import java.io.*;
import java.util.*;

public class Order implements Serializable {

    private int orderId;
    private Customer customer;                 // Aggregation
    private List<CartItem> items;               // Aggregation
    private double totalAmount;
    private Date date;
    private Payment payment;

    // Constructor
    public Order(int orderId, Customer customer, List<CartItem> items, Date date) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>(items);   // avoid external modification
        this.date = date;
        calculateTotal();
    }

    // Calculate total order amount
    public void calculateTotal() {
        totalAmount = 0;
        for (CartItem item : items) {
            totalAmount += item.getSubtotal();
        }
    }

    // Generate bill / invoice
    public void generateBill() {
        System.out.println("----------- ORDER BILL -----------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getUserName());
        System.out.println("Date: " + date);

        for (CartItem item : items) {
            System.out.println(
                    item.getProduct().getName() +
                            " x " + item.getQuantity() +
                            " = " + item.getSubtotal()
            );
        }

        System.out.println("Total Amount: " + totalAmount);
        System.out.println("----------------------------------");
    }

    // ---------------- FILE HANDLING ----------------

    public void saveToFile() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("order_" + orderId + ".dat"))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error saving order.");
        }
    }

    public static Order loadFromFile(String fileName) {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            return (Order) in.readObject();
        } catch (Exception e) {
            System.out.println("Error loading order.");
            return null;
        }
    }

    // ---------------- GETTERS ----------------

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
