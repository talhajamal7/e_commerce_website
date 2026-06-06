package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.dtos.Cart;
import com.bsse5a.EcommerceWeb.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = cartService.getCart(session);

        List<CartItemView> cartItems = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            ProductDto product = cart.getProductCache().get(entry.getKey());
            if (product != null) {
                cartItems.add(new CartItemView(product, entry.getValue()));
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalItems", cart.getTotalItems());
        model.addAttribute("cartTotal", cart.getTotal());
        model.addAttribute("isEmpty", cart.isEmpty());

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        try {
            cartService.addToCart(session, productId, quantity);
            redirectAttributes.addFlashAttribute("success", "Product added to cart!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add product to cart");
        }
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable Long productId,
                                 @RequestParam int quantity,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        try {
            cartService.updateQuantity(session, productId, quantity);
            redirectAttributes.addFlashAttribute("success", "Cart updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update cart");
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeItem(@PathVariable Long productId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        try {
            cartService.removeItem(session, productId);
            redirectAttributes.addFlashAttribute("success", "Item removed from cart");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove item");
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session, RedirectAttributes redirectAttributes) {
        cartService.clearCart(session);
        redirectAttributes.addFlashAttribute("success", "Cart cleared");
        return "redirect:/cart";
    }

    public static class CartItemView {
        private ProductDto product;
        private int quantity;

        public CartItemView(ProductDto product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public ProductDto getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSubtotal() {
            return product.getPrice() * quantity;
        }
    }
}