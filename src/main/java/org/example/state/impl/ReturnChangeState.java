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

    }

    @Override
    public void insertNote(Note note) {

    }

    @Override
    public void payWithCard() {

    }

    @Override
    public void dispenseProduct(Product product) {

    }

    @Override
    public void returnChange() {

    }
}
