package org.example.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Product> productMap;
    private Map<Product, Integer> productInventory;

    private static Inventory instance;

    private Inventory() {
        productInventory = new HashMap<>();
        productMap = new HashMap<>();
        productMap.put("COKE", new Product("Coke", 1.0));
        productMap.put("PEPSI", new Product("Pepsi", 1.0));

        productInventory.put(productMap.get("COKE"), 5);
        productInventory.put(productMap.get("PEPSI"), 5);
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product product, int quantity) {
        productMap.put(product.getName().toLowerCase(), product);
        productInventory.put(product, productInventory.getOrDefault(product, 0) + quantity);
    }

    public int getQuantity(Product product) {
        return productInventory.getOrDefault(product, 0);
    }

    public boolean hasStock(Product product, int quantity) {
        return getQuantity(product) >= quantity;
    }

    public void reduceQuantity(Product product, int quantity) {
        productInventory.put(product, productInventory.get(product) - quantity);
    }

    public void removeProduct(Product product) {
        productInventory.remove(product);
    }

    public boolean contains(String productName) {
        return productMap.containsKey(productName);
    }

    public Product getProductByName(String productName) {
        return productMap.getOrDefault(productName, null);
    }

}
