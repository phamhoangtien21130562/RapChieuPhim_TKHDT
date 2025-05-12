package view;


import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterView() {
        setTitle("Đăng ký");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerButton = new JButton("Đăng ký");
        add(new JLabel()); // chỗ trống
        add(registerButton);
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
