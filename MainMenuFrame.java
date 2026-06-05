import javax.swing.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame(Customer c){
        setTitle("Menu");
        setSize(450,350);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton p=new JButton("Products");
        JButton cart=new JButton("Cart");
        JButton back=new JButton("Back");



        p.setBounds(140,60,140,40);
        cart.setBounds(140,120,140,40);
        back.setBounds(140,180,140,40);


        add(p); add(cart);add(back);

        p.addActionListener(e->{
            dispose();
            new ProductsFrame(c);
        });
        cart.addActionListener(e->{
            dispose();
            new CartFrame(c);
        });
        back.addActionListener(e->{
            dispose();
            new LoginFrame(new Authentication(), new AdminAuthentication());
        });


        setVisible(true);
    }
}



