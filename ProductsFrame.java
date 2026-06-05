import javax.swing.*;
import java.util.*;

public class ProductsFrame extends JFrame {

    private static List<ProductsFrame> openFrames = new ArrayList<>();
    private Customer customer;

    public ProductsFrame(Customer c) {
        this.customer = c;
        openFrames.add(this);

        setTitle("Products");
        setSize(450, 450);
        setLayout(null);
        setLocationRelativeTo(null);

        renderProducts();



        setVisible(true);

    }


    private void renderProducts() {
        getContentPane().removeAll(); // clear previous labels/buttons
        int y = 20;
        JButton back = new JButton("Back");
        back.setBounds(160, 370,80,25);
        add(back);
        back.addActionListener(e -> {
            dispose();
            new MainMenuFrame(customer);
        });

        for (Product p : ProductStore.getAllProducts()) {
            JLabel label = new JLabel(p.getName() + " (Price: $" + p.getPrice() +")");
            JButton addBtn = new JButton("Add");

            label.setBounds(20, y, 280, 25);
            addBtn.setBounds(320, y, 80, 25);

            add(label);
            add(addBtn);

            addBtn.addActionListener(e -> {
                if (p.getStock() > 0) {
                    customer.getCart().addItem(p, 1); // Only add to cart
                    JOptionPane.showMessageDialog(this, p.getName() + " added to cart");
                    CartFrame.refreshAll(); // update cart frames
                } else {
                    JOptionPane.showMessageDialog(this, "Out of stock!");
                }
            });

            y += 40;
        }

        revalidate();
        repaint();
    }


    // Refresh all open product frames
    public static void refreshAll() {
        for (ProductsFrame frame : openFrames) {
            frame.renderProducts();
        }
    }
}
