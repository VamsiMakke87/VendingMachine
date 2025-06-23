package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;

public class IdleState implements VendingMachineState {

    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void cancelRequest() {
        vendingMachine.setVendingMachineState(vendingMachine.getIdleState());
    }

    @Override
    public void selectProduct(Product product, int quantity) {
        vendingMachine.setSelectedProduct(product);
        vendingMachine.setSelectedQuantity(quantity);
        vendingMachine.setVendingMachineState(vendingMachine.getPaymentState());
    }

    @Override
    public void payWithCard(double amount) {
        System.out.println("Please select a product first");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product first");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct(Product product, int quantity) {
        System.out.println("Please select a product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select a product first");
    }
}
