package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;

public class ReturnChangeState implements VendingMachineState {

    private VendingMachine vendingMachine;
    public ReturnChangeState(VendingMachine vendingMachine){
        this.vendingMachine=vendingMachine;
    }
    @Override
    public void selectProduct(Product productName, int quantity) {

        System.out.println(productName.getName()+" selected, Pirce: "+ productName.getPrice());
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Total Payment: "+ vendingMachine.getAmountPaid()+" is aleady been made");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Total Payment: "+ vendingMachine.getAmountPaid()+" is aleady been made");
    }

    @Override
    public void cancelRequest() {
        System.out.println("Cannot cancel request after transaction has been complete!!");
    }

    @Override
    public void payWithCard(double amount) {
        System.out.println("Total Payment: "+ vendingMachine.getAmountPaid()+" is aleady been made");
    }

    @Override
    public void dispenseProduct(Product product, int quantity) {
        System.out.println("Product already dispensed!");
    }

    @Override
    public void returnChange() {
        vendingMachine.printPaymentStatus();
        System.out.println("Refunded amount: $"+ vendingMachine.getAmountToBeRefunded());
        vendingMachine.clear();
    }
}
