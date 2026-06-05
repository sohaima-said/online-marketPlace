import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CartFrame extends JFrame {

    private static List<CartFrame> openCarts = new ArrayList<>();
    private Customer customer;

    public CartFrame(Customer c) {
        this.customer = c;
        openCarts.add(this);

        setTitle("Cart");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        renderCart();

        setVisible(true);
    }

    private void renderCart() {
        getContentPane().removeAll();
        JButton back = new JButton("Back");
        back.setBounds(160, 220,80,25);
        add(back);
        back.addActionListener(e -> {
            dispose();
            new MainMenuFrame(customer);
        });
        int y = 20;

        for (CartItem item : customer.getCart().getItems()) {
            JLabel label = new JLabel(item.getProduct().getName() + " x " + item.getQuantity());
            label.setBounds(20, y, 250, 25);
            add(label);
            y += 30;
        }

        JButton checkout = new JButton("Checkout");
        checkout.setBounds(120, y, 150, 30);
        add(checkout);

        checkout.addActionListener(e -> {
            dispose();
            new CheckoutFrame(customer);
            refreshAll();
        });

        revalidate();
        repaint();
    }

    // Refresh all open cart frames
    public static void refreshAll() {
        for (CartFrame frame : openCarts) {
            frame.renderCart();
        }
    }
}
