package org.example;

import org.example.currency.Coin;
import org.example.currency.Note;
import org.example.model.Inventory;
import org.example.model.Product;
import org.example.state.VendingMachineState;
import org.example.state.impl.DispenseState;
import org.example.state.impl.IdleState;
import org.example.state.impl.PaymentState;
import org.example.state.impl.ReturnChangeState;

public class VendingMachine{

    private static VendingMachine instance;
    private IdleState idleState;
    private PaymentState paymentState;
    private DispenseState dispenseState;
    private ReturnChangeState returnChangeState;

    private VendingMachineState vendingMachineState;

    private double amountPaid;

    private double totalAmount;
    private double remainingAmount;
    private double amountToBeRefunded;

    private Inventory inventory;

    private Product selectedProduct;


    private int selectedQuantity;

    private VendingMachine() {
        amountPaid = 0;
        inventory = Inventory.getInstance();
        idleState = new IdleState(this);
        paymentState = new PaymentState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);
        setVendingMachineState(idleState);
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public IdleState getIdleState() {
        return idleState;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public ReturnChangeState getReturnChangeState() {
        return returnChangeState;
    }

    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }

    public synchronized double getAmountPaid() {
        return amountPaid;
    }

    // synchronized to handle cases when muliple currency denominations like Coin and Note is inserted
    public synchronized void setAmountPaid(double amountPaid) {
        this.amountPaid += amountPaid;
        setRemainingAmount();
        printPaymentStatus();
    }


    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = Math.max(1, selectedQuantity); // Atleast one quantity is selected
        totalAmount = selectedProduct.getPrice() * selectedQuantity;
        setAmountPaid(0);
    }

    public void selectProduct(String productName, int quantity) {
        Product product = inventory.getProductByName(productName.toUpperCase());
        if (product != null) {
            if (inventory.hasStock(product, quantity)) {
                vendingMachineState.selectProduct(product, quantity);
            } else {
                System.out.println("The vending machine currently has only " + inventory.getQuantity(product) + " units of " + productName + " available.");
            }
        } else {
            System.out.println("Product do not exist");
        }
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    private synchronized void setRemainingAmount() {
        remainingAmount = totalAmount - amountPaid;
        double roundedRemainingAmount = Math.round(remainingAmount * 100.0) / 100.0;
        remainingAmount=roundedRemainingAmount;
    }

    public double getAmountToBeRefunded() {
        return amountToBeRefunded;
    }

    public void setAmountToBeRefunded(double amountToBeRefunded) {
        this.amountToBeRefunded = amountToBeRefunded;
        System.out.println("Amount to be refunded:" + amountToBeRefunded);
    }

    public void printPaymentStatus() {
        System.out.println("-------------------------------------------------");
        System.out.println("Product: " + selectedProduct.getName());
        System.out.println("Quantity: " + selectedQuantity);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Amount Paid: $" + amountPaid);
        System.out.println("Remaining Amount: $" + remainingAmount);
        System.out.println("-------------------------------------------------");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void insertCoin(Coin coin) {
        vendingMachineState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        vendingMachineState.insertNote(note);
    }

    public void payWithCard(double amount) {
        vendingMachineState.payWithCard(amount);
    }

    public void cancelRequest(){
        vendingMachineState.cancelRequest();
    }

    public void clear(){
        selectedProduct=null;
        totalAmount=0;
        selectedQuantity=0;
        amountPaid=0;
        remainingAmount=0;
        amountToBeRefunded=0;
        vendingMachineState=idleState;
    }
}
