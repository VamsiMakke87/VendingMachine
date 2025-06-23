package org.example.strategy.payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println(amount+" inserted");
        return true;
    }
}
