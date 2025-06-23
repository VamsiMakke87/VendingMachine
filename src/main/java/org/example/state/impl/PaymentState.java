package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;
import org.example.strategy.payment.CardPaymentStrategy;
import org.example.strategy.payment.CashPaymentStrategy;
import org.example.strategy.payment.PaymentStrategy;

public class PaymentState implements VendingMachineState {

    private PaymentStrategy paymentStrategy;

    private VendingMachine vendingMachine;

    public PaymentState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.paymentStrategy = new CashPaymentStrategy(); // Default payment strategy
    }

    private synchronized void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void selectProduct(Product productName, int quantity) {

        System.out.println("Product: " + productName + " is already selected, please pay amount:" + quantity);
    }

    @Override
    public synchronized void insertCoin(Coin coin) {
        if (paymentStrategy instanceof CardPaymentStrategy) {
            setPaymentStrategy(new CashPaymentStrategy());
        }
        double amount = coin.getValue();
        if (paymentStrategy.pay(amount)) {
            vendingMachine.setAmountPaid(amount);
        } else {
            System.out.println("Transcation Failed!!");
        }
        if (vendingMachine.getRemainingAmount() <= 0) {
            vendingMachine.setAmountToBeRefunded(Math.abs(vendingMachine.getRemainingAmount()));
            vendingMachine.setVendingMachineState(vendingMachine.getDispenseState());
            vendingMachine.getVendingMachineState().dispenseProduct(vendingMachine.getSelectedProduct(), vendingMachine.getSelectedQuantity());
        }
    }

    @Override
    public synchronized void insertNote(Note note) {
        if (paymentStrategy instanceof CardPaymentStrategy) {
            setPaymentStrategy(new CashPaymentStrategy());
        }
        double amount = note.getValue();
        if (paymentStrategy.pay(amount)) {
            vendingMachine.setAmountPaid(amount);
        } else {
            System.out.println("Transcation Failed!!");
        }
        if (vendingMachine.getRemainingAmount() <= 0) {
            vendingMachine.setAmountToBeRefunded(Math.abs(vendingMachine.getRemainingAmount()));
            vendingMachine.setVendingMachineState(vendingMachine.getDispenseState());
            vendingMachine.getVendingMachineState().dispenseProduct(vendingMachine.getSelectedProduct(), vendingMachine.getSelectedQuantity());
        }
    }

    @Override
    public synchronized void payWithCard(double amount) {
        if(amount<=0 || vendingMachine.getRemainingAmount()<amount){
            System.out.println("Amount must be between $0.1 and $"+ vendingMachine.getRemainingAmount());
            return;
        }
        if (paymentStrategy instanceof CashPaymentStrategy) {
            setPaymentStrategy(new CardPaymentStrategy());
        }
        double roundedAmount = Math.round(amount * 100.0) / 100.0;
        if (paymentStrategy.pay(roundedAmount)) {
            vendingMachine.setAmountPaid(roundedAmount);
        } else {
            System.out.println("Transcation Failed!!");
        }
        if (vendingMachine.getRemainingAmount() <= 0) {
            vendingMachine.setAmountToBeRefunded(Math.abs(vendingMachine.getRemainingAmount()));
            vendingMachine.setVendingMachineState(vendingMachine.getDispenseState());
            vendingMachine.getVendingMachineState().dispenseProduct(vendingMachine.getSelectedProduct(), vendingMachine.getSelectedQuantity());
        }
    }

    @Override
    public void dispenseProduct(Product product, int quantity) {
        System.out.println("Please complete the payment first!");
    }

    @Override
    public void cancelRequest() {
        System.out.println("Cancelling request, redirecting to home");
        vendingMachine.setAmountToBeRefunded(vendingMachine.getAmountPaid());
        vendingMachine.setVendingMachineState(vendingMachine.getReturnChangeState());
        vendingMachine.getVendingMachineState().returnChange();
    }

    @Override
    public void returnChange() {
        System.out.println("Please complete the payment first!");
    }
}
