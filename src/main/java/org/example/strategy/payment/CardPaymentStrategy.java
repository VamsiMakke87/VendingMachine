package org.example.strategy.payment;

public class CardPaymentStrategy extends PaymentStrategy{
    public CardPaymentStrategy(double totalPayment) {
        super(totalPayment);
    }

    @Override
    public void pay(double amount) {

    }
}
