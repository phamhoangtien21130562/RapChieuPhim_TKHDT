package model;

public class MoMoPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
      
        String transactionId = "MOMO" + System.currentTimeMillis();
        return "Thanh toán MoMo thành công!\nMã giao dịch: " + transactionId + 
               "\nSố tiền: " + (int)amount + " VNĐ";
    }
    
    @Override
    public String getPaymentMethodName() {
        return "MoMo";
    }
    
    @Override
    public String getPaymentInstructions() {
        return "Mở ứng dụng MoMo và chuyển tiền theo thông tin bên dưới.";
    }
    
    @Override
    public String getAccountInfo() {
        return "Chủ tài khoản: AE Cinema\nSố điện thoại: 0901234567";
    }
}