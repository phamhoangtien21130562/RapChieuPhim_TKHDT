package model;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public String executePayment(double amount) {
        if (paymentStrategy == null) {
            return "Vui lòng chọn phương thức thanh toán!";
        }
        return paymentStrategy.processPayment(amount);
    }
    

    public String getSelectedPaymentMethod() {
        return paymentStrategy != null ? paymentStrategy.getPaymentMethodName() : "Chưa chọn";
    }
    
    
    public String getPaymentInstructions() {
        return paymentStrategy != null ? paymentStrategy.getPaymentInstructions() : "Chưa có hướng dẫn";
    }
    
    public String getAccountInfo() {
        return paymentStrategy != null ? paymentStrategy.getAccountInfo() : "Chưa có thông tin tài khoản";
    }
    
    public boolean isPaymentMethodSelected() {
        return paymentStrategy != null;
    }
}