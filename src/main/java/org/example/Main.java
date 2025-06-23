package org.example;

import org.example.currency.Coin;
import org.example.currency.Note;
public class Main {
    public static void main(String[] args) {

        VendingMachine vendingMachine = VendingMachine.getInstance();

        vendingMachine.selectProduct("coke", 3);

        vendingMachine.payWithCard(10);

        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertNote(Note.ONE);

        vendingMachine.payWithCard(0.9);

        vendingMachine.insertNote(Note.ONE);

        vendingMachine.selectProduct("coke", 1);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);

        vendingMachine.cancelRequest();

    }
}