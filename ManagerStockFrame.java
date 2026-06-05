import javax.swing.*;

public class ManagerStockFrame extends JFrame {

    private JComboBox<Product> productBox;
    private JTextField qtyField;

    // New product fields
    private JComboBox<String> typeBox;
    private JTextField idField, nameField, priceField, stockField, extraField1, extraField2;
    private JLabel extraLabel1, extraLabel2;

    public ManagerStockFrame() {

        setTitle("Manager - Product Management");
        setSize(550, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /* ================= EXISTING PRODUCT ================= */

        JLabel selectLabel = new JLabel("Select Product:");
        selectLabel.setBounds(30, 20, 120, 25);

        productBox = new JComboBox<>(ProductStore.getAllProducts().toArray(new Product[0]));
        productBox.setBounds(160, 20, 300, 25);

        JLabel qtyLabel = new JLabel("Add Quantity:");
        qtyLabel.setBounds(30, 55, 120, 25);

        qtyField = new JTextField();
        qtyField.setBounds(160, 55, 150, 25);

        JButton addStockBtn = new JButton("Add Stock");
        addStockBtn.setBounds(330, 55, 130, 25);

        /* ================= NEW PRODUCT ================= */

        JLabel newTitle = new JLabel("Add New Product");
        newTitle.setBounds(200, 100, 150, 25);

        JLabel typeLabel = new JLabel("Type:");
        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel stockLabel = new JLabel("Stock:");
        extraLabel1 = new JLabel("Extra1:");
        extraLabel2 = new JLabel("Extra2:");

        typeLabel.setBounds(30, 140, 120, 25);
        idLabel.setBounds(30, 170, 120, 25);
        nameLabel.setBounds(30, 200, 120, 25);
        priceLabel.setBounds(30, 230, 120, 25);
        stockLabel.setBounds(30, 260, 120, 25);
        extraLabel1.setBounds(30, 290, 120, 25);
        extraLabel2.setBounds(30, 320, 120, 25);

        typeBox = new JComboBox<>(new String[]{
                "Grocery",
                "Clothing",
                "Electronics"
        });

        idField = new JTextField();
        nameField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();
        extraField1 = new JTextField();
        extraField2 = new JTextField();

        typeBox.setBounds(160, 140, 300, 25);
        idField.setBounds(160, 170, 300, 25);
        nameField.setBounds(160, 200, 300, 25);
        priceField.setBounds(160, 230, 300, 25);
        stockField.setBounds(160, 260, 300, 25);
        extraField1.setBounds(160, 290, 300, 25);
        extraField2.setBounds(160, 320, 300, 25);

        JButton addProductBtn = new JButton("Add New Product");
        addProductBtn.setBounds(170, 360, 180, 30);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(170, 400, 180, 30);

        /* ================= ADD COMPONENTS ================= */

        add(selectLabel); add(productBox);
        add(qtyLabel); add(qtyField); add(addStockBtn);

        add(newTitle);
        add(typeLabel); add(typeBox);
        add(idLabel); add(idField);
        add(nameLabel); add(nameField);
        add(priceLabel); add(priceField);
        add(stockLabel); add(stockField);
        add(extraLabel1); add(extraField1);
        add(extraLabel2); add(extraField2);
        add(addProductBtn);
        add(backBtn);

        /* ================= ACTIONS ================= */

        addStockBtn.addActionListener(e -> addStock());
        addProductBtn.addActionListener(e -> addNewProduct());
        backBtn.addActionListener(e -> dispose());

        // Change extra fields dynamically
        typeBox.addActionListener(e -> updateExtraFields());

        // Initial setup
        updateExtraFields();

        setVisible(true);
    }

    private void updateExtraFields() {
        String type = (String) typeBox.getSelectedItem();

        switch (type) {
            case "Grocery":
                extraLabel1.setText("Expiry:");
                extraLabel1.setVisible(true);
                extraField1.setVisible(true);

                extraLabel2.setVisible(false);
                extraField2.setVisible(false);
                break;

            case "Clothing":
                extraLabel1.setText("Size:");
                extraLabel2.setText("Fabric:");
                extraLabel1.setVisible(true);
                extraField1.setVisible(true);
                extraLabel2.setVisible(true);
                extraField2.setVisible(true);
                break;

            case "Electronics":
                extraLabel1.setText("Warranty:");
                extraLabel2.setText("Energy Rating:");
                extraLabel1.setVisible(true);
                extraField1.setVisible(true);
                extraLabel2.setVisible(true);
                extraField2.setVisible(true);
                break;
        }
    }

    private void addStock() {
        try {
            int qty = Integer.parseInt(qtyField.getText());
            Product p = (Product) productBox.getSelectedItem();

            if (p == null || qty <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
                return;
            }

            p.addStock(qty);
            ProductStore.saveProducts();
            productBox.repaint();
            JOptionPane.showMessageDialog(this, "Stock updated!");
            qtyField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid quantity!");
        }
    }

    private void addNewProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Product p = null;
            String type = (String) typeBox.getSelectedItem();

            switch (type) {
                case "Grocery":
                    p = new Grocery(id, name, price, stock, extraField1.getText());
                    break;

                case "Clothing":
                    p = new Clothing(id, name, price, stock,
                            extraField1.getText(), extraField2.getText());
                    break;

                case "Electronics":
                    p = new Electronics(id, name, price, stock,
                            extraField1.getText(), extraField2.getText());
                    break;
            }

            if (p != null) {
                ProductStore.addProduct(p);
                ProductStore.saveProducts();
                productBox.addItem(p);

                JOptionPane.showMessageDialog(this, "New product added!");
                idField.setText(""); nameField.setText("");
                priceField.setText(""); stockField.setText("");
                extraField1.setText(""); extraField2.setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for new product!");
        }
    }
}
