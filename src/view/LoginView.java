package view;


import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Đăng nhập");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Tên đăng nhập:"));
        JTextField usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Mật khẩu:"));
        JPasswordField passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel("Bạn đã chưa có tài khoản?"));

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Đăng nhập");
        JButton registerButton = new JButton("Đăng ký");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        registerButton.addActionListener(e -> {
            new RegisterView().setVisible(true);
        });

        add(inputPanel);
        add(messagePanel);
        add(buttonPanel);
    }
}
