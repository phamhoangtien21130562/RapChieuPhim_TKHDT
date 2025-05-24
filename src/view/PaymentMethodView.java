package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentMethodView extends JFrame {
    private JLabel movieInfoLabel;
    private JLabel seatInfoLabel;
    private JLabel ticketTypeLabel;
    private JTextArea comboDetailsArea;
    private JLabel totalAmountLabel;
    private JRadioButton momoRadio;
    private JRadioButton bankRadio;
    private ButtonGroup paymentGroup;
    private JButton continueButton;
    private JButton backButton;
    
    public PaymentMethodView() {
        setTitle("Chọn phương thức thanh toán");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel thông tin đơn hàng
        JPanel orderInfoPanel = new JPanel();
        orderInfoPanel.setLayout(new BoxLayout(orderInfoPanel, BoxLayout.Y_AXIS));
        orderInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng"));
        
        movieInfoLabel = new JLabel("Tên phim: ");
        seatInfoLabel = new JLabel("Ghế: ");
        ticketTypeLabel = new JLabel("Loại vé: ");
        
        comboDetailsArea = new JTextArea(4, 30);
        comboDetailsArea.setEditable(false);
        comboDetailsArea.setBorder(BorderFactory.createTitledBorder("Combo đã chọn"));
        JScrollPane comboScroll = new JScrollPane(comboDetailsArea);
        
        totalAmountLabel = new JLabel("Tổng tiền: ");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        orderInfoPanel.add(movieInfoLabel);
        orderInfoPanel.add(Box.createVerticalStrut(5));
        orderInfoPanel.add(seatInfoLabel);
        orderInfoPanel.add(Box.createVerticalStrut(5));
        orderInfoPanel.add(ticketTypeLabel);
        orderInfoPanel.add(Box.createVerticalStrut(10));
        orderInfoPanel.add(comboScroll);
        orderInfoPanel.add(Box.createVerticalStrut(10));
        orderInfoPanel.add(totalAmountLabel);
        
        // Panel chọn phương thức thanh toán
        JPanel paymentPanel = new JPanel();
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Chọn phương thức thanh toán"));
        paymentPanel.setLayout(new GridLayout(2, 1, 10, 10));
        
        momoRadio = new JRadioButton("Thanh toán qua MoMo");
        bankRadio = new JRadioButton("Chuyển khoản ngân hàng");
        
        paymentGroup = new ButtonGroup();
        paymentGroup.add(momoRadio);
        paymentGroup.add(bankRadio);
        
        paymentPanel.add(momoRadio);
        paymentPanel.add(bankRadio);
        
        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Quay lại");
        continueButton = new JButton("Tiếp tục thanh toán");
        
        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);
        
        add(orderInfoPanel, BorderLayout.CENTER);
        add(paymentPanel, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(paymentPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    // Getters và setters
    public void setMovieInfo(String movieName, String room) {
        movieInfoLabel.setText("Tên phim: " + movieName + " - Phòng: " + room);
    }
    
    public void setSeatInfo(String seats) {
        seatInfoLabel.setText("Ghế: " + seats);
    }
    
    public void setTicketType(String ticketType) {
        ticketTypeLabel.setText("Loại vé: " + ticketType);
    }
    
    public void setComboDetails(String comboDetails) {
        comboDetailsArea.setText(comboDetails);
    }
    
    public void setTotalAmount(double amount) {
        totalAmountLabel.setText("Tổng tiền: " + (int)amount + " VNĐ");
    }
    
    public String getSelectedPaymentMethod() {
        if (momoRadio.isSelected()) return "MoMo";
        if (bankRadio.isSelected()) return "Bank";
        return null;
    }
    
    public void addContinueListener(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
