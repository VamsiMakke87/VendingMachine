package org.example.strategy.payment;

public abstract class PaymentStrategy {

    private double totalPayment;

    public PaymentStrategy(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public synchronized double getTotalPayment() {
        return totalPayment;
    }

    public synchronized void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public abstract void pay(double amount);

}
