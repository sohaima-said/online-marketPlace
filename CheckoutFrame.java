import javax.swing.*;

public class CheckoutFrame extends JFrame {

    public CheckoutFrame(Customer c) {

        setTitle("Checkout");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        double total = c.getCart().total();
        InventoryManager im = new InventoryManager();

        JLabel totalLabel = new JLabel("Total: $" + total);
        totalLabel.setBounds(90, 30, 150, 25);

        JButton cash = new JButton("Cash Payment");
        JButton card = new JButton("Card Payment");

        cash.setBounds(50, 80, 200, 30);
        card.setBounds(50, 130, 200, 30);

        add(totalLabel);
        add(cash);
        add(card);

        // ================= CASH PAYMENT =================
        cash.addActionListener(e -> {

            for (CartItem item : c.getCart().getItems()) {
                if (!im.reduce(item.getProduct(), item.getQuantity())) {
                    JOptionPane.showMessageDialog(this,
                            "Not enough stock for " + item.getProduct().getName(),
                            "Stock Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            new CashPayment().pay(total);
            c.getCart().clear();
            ProductStore.saveProducts();

            JOptionPane.showMessageDialog(this,
                    "Payment successful!\nThank you for shopping.");

            dispose();
            new CartFrame(c);
        });

        // ================= CARD PAYMENT =================
        card.addActionListener(e -> {

            String cardNumber = JOptionPane.showInputDialog(
                    this,
                    "Enter 16-digit Credit Card Number:"

            );

            if (cardNumber == null) return; // user cancelled

            String balanceInput = JOptionPane.showInputDialog(
                    this,
                    "Enter available balance in card:"
            );

            if (balanceInput == null) return;

            double balance;

            try {
                balance = Double.parseDouble(balanceInput);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid balance amount",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            CreditCardPayment payment =
                    new CreditCardPayment(cardNumber,balance);

            if (!payment.pay(total)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid Card number",
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // reduce stock after successful payment
            for (CartItem item : c.getCart().getItems()) {
                if (!im.reduce(item.getProduct(), item.getQuantity())) {
                    JOptionPane.showMessageDialog(this,
                            "Not enough stock for " + item.getProduct().getName());
                    return;
                }
            }

            c.getCart().clear();
            ProductStore.saveProducts();

            JOptionPane.showMessageDialog(
                    this,
                    "Card payment successful!"
            );

            dispose();
            new CartFrame(c);
        });
        setVisible(true);
    }
}


