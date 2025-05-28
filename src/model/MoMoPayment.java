package model;

public class MoMoPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
         return "Thanh toán thành công qua Momo\nSố tiền: " + (int)amount + " VNĐ";
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
