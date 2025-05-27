package model;

public interface PaymentStrategy {
    String processPayment(double amount);
    String getPaymentMethodName();
    String getPaymentInstructions(); 
    String getAccountInfo(); 
}