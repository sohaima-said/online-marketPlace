import javax.swing.*;

public class LoginFrame extends JFrame {

    private boolean isRegisterMode = true;
    private boolean isAdminMode = false;

    public LoginFrame(Authentication cAuth, AdminAuthentication aAuth) {

        setTitle("User Authentication");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= LABELS & FIELDS =================
        JLabel title = new JLabel("User Registration");
        title.setBounds(190, 10, 250, 35);

        // User fields
        JLabel uLabel = new JLabel("Username:");
        JLabel eLabel = new JLabel("Email:");
        JTextField user = new JTextField();
        JTextField email = new JTextField();

        uLabel.setBounds(90, 50, 80, 25);
        user.setBounds(190, 50, 170, 25);
        eLabel.setBounds(90, 100, 80, 25);
        email.setBounds(190, 100, 170, 25);

        // Admin fields
        JLabel idLabel = new JLabel("Admin ID:");
        JLabel passLabel = new JLabel("Password:");
        JTextField adminId = new JTextField();
        JPasswordField adminPass = new JPasswordField();

        idLabel.setBounds(90, 50, 80, 25);
        adminId.setBounds(190, 50, 170, 25);
        passLabel.setBounds(90, 100, 80, 25);
        adminPass.setBounds(190, 100, 170, 25);

        // Buttons
        JButton actionBtn = new JButton("Register");
        JButton switchBtn = new JButton("Already Registered? Login");
        JButton adminBtn = new JButton("Admin Login");

        actionBtn.setBounds(70, 150, 110, 30);
        switchBtn.setBounds(200, 150, 200, 30);
        adminBtn.setBounds(150, 220, 120, 30);

        // ================= ADD DEFAULT (USER VIEW) =================
        add(title);
        add(uLabel);
        add(user);
        add(eLabel);
        add(email);
        add(actionBtn);
        add(switchBtn);
        add(adminBtn);

        // ================= MAIN ACTION BUTTON =================
        actionBtn.addActionListener(e -> {

            if (!isAdminMode) {
                // ---------- USER ----------
                if (user.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required!");
                    return;
                }

                if (isRegisterMode) {
                    if (cAuth.register(user.getText(), email.getText())) {
                        JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
                        isRegisterMode = false;
                        title.setText("User Login");
                        actionBtn.setText("Login");
                        switchBtn.setText("New User? Register");
                        user.setText("");
                        email.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "User already exists!");
                    }
                } else {
                    if (cAuth.login(user.getText(), email.getText())) {
                        Customer c = cAuth.getCustomer(user.getText());
                        new MainMenuFrame(c);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or email!");
                    }
                }

            } else {
                // ---------- ADMIN ----------
                if (adminId.getText().trim().isEmpty() || adminPass.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(this, "All fields are required!");
                    return;
                }

                try {
                    int id = Integer.parseInt(adminId.getText());
                    String pass = new String(adminPass.getPassword());

                    if (aAuth.login(id, pass)) {
                        JOptionPane.showMessageDialog(this, "Admin login successful!");
                        Admin a = aAuth.getAdmin(id);
                        new AdminMenuFrame(a, cAuth);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid admin ID or password!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Admin ID must be numeric!");
                }
            }
        });

        // ================= SWITCH USER LOGIN / REGISTER =================
        switchBtn.addActionListener(e -> {

            if (isAdminMode) {
                // back to user view
                isAdminMode = false;
                isRegisterMode = true;

                remove(idLabel);
                remove(adminId);
                remove(passLabel);
                remove(adminPass);

                add(uLabel);
                add(user);
                add(eLabel);
                add(email);
                add(adminBtn);

                title.setText("User Registration");
                actionBtn.setText("Register");
                switchBtn.setText("Already Registered? Login");

            } else {
                isRegisterMode = !isRegisterMode;
                title.setText(isRegisterMode ? "User Registration" : "User Login");
                actionBtn.setText(isRegisterMode ? "Register" : "Login");
                switchBtn.setText(isRegisterMode
                        ? "Already Registered? Login"
                        : "New User? Register");
            }

            user.setText("");
            email.setText("");
            adminId.setText("");
            adminPass.setText("");

            revalidate();
            repaint();
        });

        // ================= ADMIN BUTTON =================
        adminBtn.addActionListener(e -> {

            isAdminMode = true;

            remove(uLabel);
            remove(user);
            remove(eLabel);
            remove(email);
            remove(adminBtn);

            add(idLabel);
            add(adminId);
            add(passLabel);
            add(adminPass);

            title.setText("Admin Login");
            actionBtn.setText("Login");
            switchBtn.setText("Back to User");

            revalidate();
            repaint();
        });

        setVisible(true);
    }

}
