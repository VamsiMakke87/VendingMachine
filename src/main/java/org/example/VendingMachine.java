package org.example;

import org.example.model.Inventory;
import org.example.model.Product;
import org.example.state.VendingMachineState;
import org.example.state.impl.DispenseState;
import org.example.state.impl.IdleState;
import org.example.state.impl.PaymentState;
import org.example.state.impl.ReturnChangeState;

public class VendingMachine {

    private static VendingMachine instance;
    private IdleState idleState;
    private PaymentState paymentState;
    private DispenseState dispenseState;
    private ReturnChangeState returnChangeState;

    private VendingMachineState vendingMachineState;

    private double amountPaid;

    private double remainingAmount;

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

    public synchronized double getAmountPaid() {
        return amountPaid;
    }

    // synchronized to handle cases when muliple currency denominations like Coin and Note is inserted
    public synchronized void setAmountPaid(double amountPaid) {
        this.amountPaid += amountPaid;
        setRemainingAmount(amountPaid);
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
        this.selectedQuantity = Math.min(1, selectedQuantity); // Atleast one quantity is selected
        setAmountPaid(0);
        setRemainingAmount(selectedProduct.getPrice() * selectedQuantity);
    }

    public void selectProduct(String productName, int quantity) {
        Product product = inventory.getProductByName(productName);
        if (product != null) {
            vendingMachineState.selectProduct(product, quantity);
        } else {
            System.out.println("Product do not exist");
        }
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    private synchronized void setRemainingAmount(double amount) {
        remainingAmount -= amount;
    }

    public void printPaymentStatus() {
        System.out.println("Product: " + selectedProduct.getName());
        System.out.println("Quantity: " + selectedQuantity);
        System.out.println("Total Amount: " + selectedProduct.getPrice() * selectedQuantity);
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Remaining Amount: " + remainingAmount);
    }
}
