// Controller cho payment flow
package controller;

import model.*;
import view.*;
import javax.swing.*;

public class PaymentController {
    private PaymentMethodView paymentMethodView;
    private PaymentConfirmView paymentConfirmView;
    private PaymentContext paymentContext;
    
    // Thông tin từ màn hình trước
    private String movieName;
    private String room;
    private String seats;
    private String ticketType;
    private String comboDetails;
    private double totalAmount;
    
    public PaymentController(String movieName, String room, String seats, 
                           String ticketType, String comboDetails, double totalAmount) {
        this.movieName = movieName;
        this.room = room;
        this.seats = seats;
        this.ticketType = ticketType;
        this.comboDetails = comboDetails;
        this.totalAmount = totalAmount;
        
        this.paymentContext = new PaymentContext();
        initPaymentMethodView();
    }
    
    private void initPaymentMethodView() {
        paymentMethodView = new PaymentMethodView();
        
        // Set thông tin đơn hàng
        paymentMethodView.setMovieInfo(movieName, room);
        paymentMethodView.setSeatInfo(seats);
        paymentMethodView.setTicketType(ticketType);
        paymentMethodView.setComboDetails(comboDetails);
        paymentMethodView.setTotalAmount(totalAmount);
        
        // Add listeners
        paymentMethodView.addContinueListener(e -> handleContinuePayment());
        paymentMethodView.addBackListener(e -> handleBackToMainView());
        
        paymentMethodView.setVisible(true);
    }
    
    private void handleContinuePayment() {
        String selectedMethod = paymentMethodView.getSelectedPaymentMethod();
        
        if (selectedMethod == null) {
            JOptionPane.showMessageDialog(paymentMethodView, 
                "Vui lòng chọn phương thức thanh toán!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Set payment strategy
        if ("MoMo".equals(selectedMethod)) {
            paymentContext.setPaymentStrategy(new MoMoPayment());
        } else if ("Bank".equals(selectedMethod)) {
            paymentContext.setPaymentStrategy(new BankPayment());
        }
        
        // Process payment
        String paymentResult = paymentContext.executePayment(totalAmount);
        
        // Show confirmation screen
        showPaymentConfirmation(selectedMethod, paymentResult);
        paymentMethodView.dispose();
    }
    
    private void showPaymentConfirmation(String method, String paymentResult) {
        paymentConfirmView = new PaymentConfirmView();
        
        // Extract transaction ID from result (simple parsing)
        String transactionId = extractTransactionId(paymentResult);
        
        paymentConfirmView.setPaymentResult(method, totalAmount, transactionId);
        
        // Generate ticket info
        String ticketInfo = generateTicketInfo();
        paymentConfirmView.setTicketInfo(ticketInfo);
        
        paymentConfirmView.addHomeListener(e -> {
            paymentConfirmView.dispose();
            // Navigate to home - có thể integrate với MainView hiện tại
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
        
        paymentConfirmView.addPrintListener(e -> {
            JOptionPane.showMessageDialog(paymentConfirmView, 
                "Vé đã được in thành công!", 
                "In vé", JOptionPane.INFORMATION_MESSAGE);
        });
        
        paymentConfirmView.setVisible(true);
    }
    
    private String extractTransactionId(String paymentResult) {
        // Simple extraction - có thể improve sau
        String[] lines = paymentResult.split("\n");
        for (String line : lines) {
            if (line.contains("Mã giao dịch:")) {
                return line.substring(line.indexOf(":") + 1).trim();
            }
        }
        return "N/A";
    }
    
        
    private void handleBackToMainView() {
        paymentMethodView.dispose();
        
        // Tạo lại MainView với thông tin hiện tại
        MainView mainView = new MainView();
        mainView.restoreData(movieName, room, seats, ticketType, comboDetails, totalAmount);
        mainView.showComboScreen(); // Chuyển đến màn hình combo
        mainView.setVisible(true);
    }
    
    private String generateTicketInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== THÔNG TIN VÉ ===\n");
        sb.append("Phim: ").append(movieName).append("\n");
        sb.append("Phòng: ").append(room).append("\n");
        sb.append("Ghế: ").append(seats).append("\n");
        sb.append("Loại vé: ").append(ticketType).append("\n");
        sb.append("\n=== COMBO ===\n");
        sb.append(comboDetails.isEmpty() ? "Không có combo" : comboDetails);
        sb.append("\n\n=== THANH TOÁN ===\n");
        sb.append("Phương thức: ").append(paymentContext.getSelectedPaymentMethod()).append("\n");
        sb.append("Tổng tiền: ").append((int)totalAmount).append(" VNĐ\n");
        sb.append("\nCảm ơn bạn đã sử dụng dịch vụ!");
        
        return sb.toString();
    }
}