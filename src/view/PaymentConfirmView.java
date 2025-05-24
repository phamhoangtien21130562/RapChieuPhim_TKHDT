// 2. Màn hình xác nhận thanh toán
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentConfirmView extends JFrame {
    private JLabel paymentMethodLabel;
    private JLabel amountLabel;
    private JLabel transactionLabel;
    private JTextArea ticketInfoArea;
    private JButton homeButton;
    private JButton printButton;
    
    public PaymentConfirmView() {
        setTitle("Xác nhận thanh toán");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel thông tin thanh toán
        JPanel paymentInfoPanel = new JPanel();
        paymentInfoPanel.setLayout(new BoxLayout(paymentInfoPanel, BoxLayout.Y_AXIS));
        paymentInfoPanel.setBorder(BorderFactory.createTitledBorder("Kết quả thanh toán"));
        
        paymentMethodLabel = new JLabel("Phương thức: ");
        amountLabel = new JLabel("Số tiền: ");
        transactionLabel = new JLabel("Mã giao dịch: ");
        
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        transactionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        paymentInfoPanel.add(paymentMethodLabel);
        paymentInfoPanel.add(Box.createVerticalStrut(10));
        paymentInfoPanel.add(amountLabel);
        paymentInfoPanel.add(Box.createVerticalStrut(10));
        paymentInfoPanel.add(transactionLabel);
        
        // Panel thông tin vé
        ticketInfoArea = new JTextArea(8, 30);
        ticketInfoArea.setEditable(false);
        ticketInfoArea.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
        JScrollPane ticketScroll = new JScrollPane(ticketInfoArea);
        
        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        printButton = new JButton("In vé");
        homeButton = new JButton("Về trang chủ");
        
        buttonPanel.add(printButton);
        buttonPanel.add(homeButton);
        
        add(paymentInfoPanel, BorderLayout.NORTH);
        add(ticketScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void setPaymentResult(String method, double amount, String transactionId) {
        paymentMethodLabel.setText("Phương thức: " + method);
        amountLabel.setText("Số tiền: " + (int)amount + " VNĐ");
        transactionLabel.setText("Mã giao dịch: " + transactionId);
    }
    
    public void setTicketInfo(String ticketInfo) {
        ticketInfoArea.setText(ticketInfo);
    }
    
    public void addHomeListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
    
    public void addPrintListener(ActionListener listener) {
        printButton.addActionListener(listener);
    }
}