package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.dtos.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "SHOPPING_CART";

    @Autowired
    private ProductService productService;

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, Long productId, int quantity) {
        ProductDto product = productService.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Cart cart = getCart(session);
        cart.addItem(product, quantity);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void updateQuantity(HttpSession session, Long productId, int quantity) {
        Cart cart = getCart(session);
        cart.updateQuantity(productId, quantity);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeItem(HttpSession session, Long productId) {
        Cart cart = getCart(session);
        cart.removeItem(productId);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    public int getCartItemCount(HttpSession session) {
        Cart cart = getCart(session);
        return cart.getTotalItems();
    }
}