package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;

public class DispenseState implements VendingMachineState {

    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

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
        System.out.println("Dispensing "+ product.getName()+" of quantity "+ quantity);
        vendingMachine.getInventory().reduceQuantity(product,quantity);
        vendingMachine.setVendingMachineState(vendingMachine.getReturnChangeState());
        vendingMachine.getVendingMachineState().returnChange();
    }

    @Override
    public void returnChange() {
        System.out.println("Refund will be made after dispensing the product");
    }
}
