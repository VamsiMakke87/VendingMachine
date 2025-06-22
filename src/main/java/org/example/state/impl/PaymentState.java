package org.example.state.impl;

import org.example.VendingMachine;
import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;

public class ReadyState implements VendingMachineState {

    private VendingMachine vendingMachine;
    public ReadyState(VendingMachine vendingMachine){
        this.vendingMachine=vendingMachine;
    }
    @Override
    public void selectProduct(Product product) {

        System.out.println(product.getName()+" selected, Pirce: "+ product.getPrice());
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
