package org.example.state;

import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Product;

public interface VendingMachineState {

    public void selectProduct(Product productName, int quantity);

    public void insertCoin(Coin coin);

    public void insertNote(Note note);

    public void payWithCard();

    public void dispenseProduct(Product product);

    public void returnChange();

    public void cancelRequest();

}
