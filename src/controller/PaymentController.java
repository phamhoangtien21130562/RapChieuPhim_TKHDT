package controller;

import model.*;
import java.awt.CardLayout;
import view.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PaymentController {
    private PaymentMethodView paymentMethodView;
    private PaymentDetailView paymentDetailView;
    private PaymentSuccessView paymentSuccessView;
    private PaymentProcessor paymentProcessor;
    private MainView originalMainView; 
    
    // Thông tin từ màn hình trước
    private String showSchedule; 
    private String movieName;
    private String room;
    private String seats;
    private String ticketType;
    private String comboDetails;
    private double totalAmount;
    private String invoiceCode;
    private String ticketCode;

    public PaymentController(String movieName, String room, String seats, 
            String ticketType, String comboDetails, double totalAmount,
            MainView originalMainView, String showSchedule) { 
           this.movieName = movieName;
           this.room = room;
           this.seats = seats;
           this.ticketType = ticketType;
           this.comboDetails = comboDetails;
           this.totalAmount = totalAmount;
           this.originalMainView = originalMainView;
           this.showSchedule = showSchedule; 

           this.paymentProcessor = new PaymentProcessor();
           long currentTime = System.currentTimeMillis();
           this.invoiceCode = "HD" + currentTime;
           this.ticketCode = "VE" + currentTime + (int)(Math.random() * 1000);

           initPaymentMethodView();
    }
    
    private void initPaymentMethodView() {
        paymentMethodView = new PaymentMethodView();
        
        paymentMethodView.setMovieInfo(movieName, room);
        paymentMethodView.setSeatInfo(seats);
        paymentMethodView.setTicketType(ticketType);
        paymentMethodView.setComboDetails(comboDetails);
        paymentMethodView.setTotalAmount(totalAmount);
        paymentMethodView.setScheduleInfo(showSchedule); 
        
        // Sử dụng PaymentProcessor để hiển thị thông tin phương thức đã chọn
        paymentMethodView.setSelectedMethodInfo(paymentProcessor.getSelectedPaymentMethod());
        
        paymentMethodView.addContinueListener(e -> handleContinuePayment());
        paymentMethodView.addBackListener(e -> handleBackToMainView());
        
        // Thêm listener để cập nhật thông tin khi chọn phương thức thanh toán
        paymentMethodView.addPaymentMethodChangeListener(e -> handlePaymentMethodChange());
        
        paymentMethodView.setVisible(true);
    }
    
    // Method mới để xử lý khi thay đổi phương thức thanh toán
    private void handlePaymentMethodChange() {
        String selectedMethod = paymentMethodView.getSelectedPaymentMethod();
        
        if (selectedMethod != null) {
            // Set strategy dựa trên lựa chọn
            if ("MoMo".equals(selectedMethod)) {
                paymentProcessor.setPaymentStrategy(new MoMoPayment());
            } else if ("Bank".equals(selectedMethod)) {
                paymentProcessor.setPaymentStrategy(new BankPayment());
            }
            
            // Cập nhật UI với thông tin từ PaymentProcessor 
            paymentMethodView.setSelectedMethodInfo(paymentProcessor.getSelectedPaymentMethod());
            paymentMethodView.setPaymentInstructions(paymentProcessor.getPaymentInstructions());
        }
    }
    
    private void handleContinuePayment() {
        // Kiểm tra xem đã chọn phương thức thanh toán chưa 
        if (!paymentProcessor.isPaymentMethodSelected()) {
            JOptionPane.showMessageDialog(paymentMethodView, 
                "Vui lòng chọn phương thức thanh toán!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Hiển thị thông tin phương thức đã chọn
        String methodInfo = "Phương thức đã chọn: " + paymentProcessor.getSelectedPaymentMethod();
        System.out.println(methodInfo); 
        
        // Show payment detail screen với thông tin từ PaymentProcessor
        showPaymentDetail();
        paymentMethodView.dispose();
    }
    
    private void showPaymentDetail() {
        paymentDetailView = new PaymentDetailView();
        
        // Sử dụng PaymentProcessor để lấy thông tin 
        paymentDetailView.setPaymentDetails(
            paymentProcessor.getSelectedPaymentMethod(), 
            totalAmount, 
            invoiceCode,
            paymentProcessor.getAccountInfo(),
            paymentProcessor.getPaymentInstructions()
        );
        
        paymentDetailView.addBackListener(e -> {
            paymentDetailView.dispose();
            paymentMethodView.setVisible(true);
        });
        
        paymentDetailView.addConfirmListener(e -> {
            // Process payment
            String paymentResult = paymentProcessor.executePayment(totalAmount);
            System.out.println("Kết quả thanh toán: " + paymentResult);
            
            showPaymentSuccess();
            paymentDetailView.dispose();
        });
        
        paymentDetailView.setVisible(true);
    }
    
    private void showPaymentSuccess() {
        paymentSuccessView = new PaymentSuccessView();
        
        // lấy tên phương thức thanh toán
        paymentSuccessView.setPaymentResult(
            totalAmount, 
            paymentProcessor.getSelectedPaymentMethod(), 
            invoiceCode, 
            ticketCode
        );
        
        paymentSuccessView.addHomeListener(e -> {
            paymentSuccessView.dispose();
            originalMainView.setVisible(true);
        });
        
        paymentSuccessView.setVisible(true);
    }
        
    private void handleBackToMainView() {
        paymentMethodView.dispose();
        originalMainView.setVisible(true);
    }
}
