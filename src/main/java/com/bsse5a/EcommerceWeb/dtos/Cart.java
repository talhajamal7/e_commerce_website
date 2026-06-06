package com.bsse5a.EcommerceWeb.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Long, Integer> items = new HashMap<>();

    private Map<Long, ProductDto> productCache = new HashMap<>();

    public void addItem(ProductDto product, int quantity) {
        Long productId = product.getId();
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
        productCache.put(productId, product);
    }

    public void updateQuantity(Long productId, int quantity) {
        if (quantity <= 0) {
            removeItem(productId);
        } else {
            items.put(productId, quantity);
        }
    }

    public void removeItem(Long productId) {
        items.remove(productId);
        productCache.remove(productId);
    }

    public void clear() {
        items.clear();
        productCache.clear();
    }

    public int getQuantity(Long productId) {
        return items.getOrDefault(productId, 0);
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public Map<Long, ProductDto> getProductCache() {
        return productCache;
    }

    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> {
                    ProductDto product = productCache.get(entry.getKey());
                    return product != null ? product.getPrice() * entry.getValue() : 0.0;
                })
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}