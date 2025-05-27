package model;

public class BankPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
      
        String transactionId = "BANK" + System.currentTimeMillis();
        return "Thanh toán ngân hàng thành công!\nMã giao dịch: " + transactionId + 
               "\nSố tiền: " + (int)amount + " VNĐ";
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Chuyển khoản ngân hàng";
    }
    
    @Override
    public String getPaymentInstructions() {
        return "Vui lòng chuyển khoản theo thông tin bên dưới và giữ lại biên lai giao dịch.";
    }
    
    @Override
    public String getAccountInfo() {
        return "Chủ tài khoản: AE Cinema\nSố tài khoản: 1234567890 - Ngân hàng MB Bank";
    }
}