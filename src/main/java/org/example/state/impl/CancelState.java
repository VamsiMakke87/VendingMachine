package org.example.state.impl;

import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;
import org.example.state.VendingMachineState;

public class CancelState implements VendingMachineState {
    @Override
    public void selectProduct(Product productName, int quantity) {

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

    @Override
    public void cancelRequest() {

    }
}
