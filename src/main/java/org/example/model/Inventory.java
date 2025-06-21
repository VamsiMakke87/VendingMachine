package org.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {

    private Map<Product, Integer> productInventory;

    private Inventory instance;

    private Inventory() {
        productInventory = new LinkedHashMap<>();
    }

    public synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product product, int quantity) {
        productInventory.put(product, productInventory.getOrDefault(product, 0) + quantity);
    }

    public int getQuantity(Product product) {
        return productInventory.getOrDefault(product, 0);
    }

    public boolean hasStock(Product product,int quantity) {
        return getQuantity(product) > quantity;
    }

    public void reduceQuantity(Product product,int quantity){
        productInventory.put(product,productInventory.get(product)-quantity);
    }


}
