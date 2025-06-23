package org.example.strategy.payment;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double amount) {
        // some card payment logic
        System.out.println(amount+ " paid through card");

        return  true;
    }
}
