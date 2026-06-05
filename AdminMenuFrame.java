import javax.swing.*;
import java.util.List;

public class AdminMenuFrame extends JFrame {

    private Admin admin;

    public AdminMenuFrame(Admin admin, Authentication cAuth) {
        this.admin = admin;

        setTitle("Admin Panel");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Welcome Admin");
        title.setBounds(130, 20, 200, 30);

        // Admin info
        JLabel nameLabel = new JLabel("Name: " + admin.getUserName());
        JLabel idLabel = new JLabel("Admin ID: " + admin.getId());
        JLabel emailLabel = new JLabel("Email: " + admin.getEmail());

        nameLabel.setBounds(25, 70, 300, 25);
        idLabel.setBounds(25, 100, 300, 25);
        emailLabel.setBounds(25, 130, 300, 25);

        // Buttons
        JButton manageProductsBtn = new JButton("Manage Products");
        JButton viewUsersBtn = new JButton("View Customers");
        JButton logoutBtn = new JButton("Logout");

        manageProductsBtn.setBounds(100, 180, 180, 30);
        viewUsersBtn.setBounds(100, 220, 180, 30);
        logoutBtn.setBounds(100, 260, 180, 30);

        // Button actions
        manageProductsBtn.addActionListener(e -> {
            new ManagerStockFrame(); // open stock management
        });

        viewUsersBtn.addActionListener(e -> {

                List<Customer> users = cAuth.loadUsers();

                if (users == null || users.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No customers found.");
                    return;
                }

                JTextArea area = new JTextArea();
                area.setEditable(false);

                for (User u : users) {
                    area.append(
                                    "Name: " + u.getUserName() + "\n" +
                                    "Email: " + u.getEmail() + "\n" +
                                    "---------------------------\n"
                    );
                }

                JScrollPane scrollPane = new JScrollPane(area);
                scrollPane.setPreferredSize(new java.awt.Dimension(350, 250));

                JOptionPane.showMessageDialog(this, scrollPane, "Customer List",
                        JOptionPane.INFORMATION_MESSAGE);


        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame(new Authentication(), new AdminAuthentication());
        });

        // Add components
        add(title);
        add(nameLabel);
        add(idLabel);
        add(emailLabel);
        add(manageProductsBtn);
        add(viewUsersBtn);
        add(logoutBtn);

        setVisible(true);
    }
}
