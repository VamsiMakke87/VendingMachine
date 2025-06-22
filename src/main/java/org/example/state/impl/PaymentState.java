package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;
import org.example.strategy.payment.PaymentStrategy;

public class PaymentState implements VendingMachineState {

    private PaymentStrategy paymentStrategy;

    @Override
    public void payWithCard() {
        System.out.println("Some card payment handling logic");
        double remainingAmount=vendingMachine.getRemainingAmount();
        vendingMachine.setAmountPaid(remainingAmount);
        System.out.println("Amount paid:"+ remainingAmount);
    }

    public PaymentState(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    private VendingMachine vendingMachine;
    public PaymentState(VendingMachine vendingMachine){
        this.vendingMachine=vendingMachine;
    }
    @Override
    public void selectProduct(Product productName, int quantity) {

        System.out.println("Product: "+ productName +" is already selected, please pay amount:"+quantity );
    }

    @Override
    public void insertCoin(Coin coin) {

    }

    @Override
    public void insertNote(Note note) {

    }

    @Override
    public void dispenseProduct(Product product) {

    }

    @Override
    public void returnChange() {

    }
}
