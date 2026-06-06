package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.Cart;
import com.bsse5a.EcommerceWeb.models.Order;
import com.bsse5a.EcommerceWeb.services.CartService;
import com.bsse5a.EcommerceWeb.services.OrderService;
import com.bsse5a.EcommerceWeb.services.OrderService.OrderCheckoutDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private static final double FREE_SHIPPING_THRESHOLD = 5000.0;
    private static final double STANDARD_SHIPPING_COST = 250.0;

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Cart cart = cartService.getCart(session);

        if (cart == null || cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty. Please add items before checkout.");
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("productCache", cart.getProductCache());
        model.addAttribute("subtotal", cart.getTotal());
        model.addAttribute("totalItems", cart.getTotalItems());

        double shippingCost = cart.getTotal() >= FREE_SHIPPING_THRESHOLD ? 0.0 : STANDARD_SHIPPING_COST;
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("total", cart.getTotal() + shippingCost);
        model.addAttribute("freeShippingThreshold", FREE_SHIPPING_THRESHOLD);

        if (!model.containsAttribute("checkoutDto")) {
            model.addAttribute("checkoutDto", new OrderCheckoutDto());
        }

        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@Valid @ModelAttribute("checkoutDto") OrderCheckoutDto checkoutDto,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        Cart cart = cartService.getCart(session);

        if (cart == null || cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
            return "redirect:/cart";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            model.addAttribute("cartItems", cart.getItems());
            model.addAttribute("productCache", cart.getProductCache());
            model.addAttribute("subtotal", cart.getTotal());
            model.addAttribute("totalItems", cart.getTotalItems());

            double shippingCost = cart.getTotal() >= FREE_SHIPPING_THRESHOLD ? 0.0 : STANDARD_SHIPPING_COST;
            model.addAttribute("shippingCost", shippingCost);
            model.addAttribute("total", cart.getTotal() + shippingCost);
            model.addAttribute("freeShippingThreshold", FREE_SHIPPING_THRESHOLD);

            return "checkout";
        }

        try {
            Order order = orderService.createOrderFromCart(cart, checkoutDto);

            cart.clear();

            cartService.clearCart(session);

            redirectAttributes.addFlashAttribute("success", "Order placed successfully!");
            return "redirect:/orders/confirmation/" + order.getId();

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to process order: " + e.getMessage());
            return "redirect:/orders/checkout";
        }
    }

    @GetMapping("/confirmation/{orderId}")
    public String showOrderConfirmation(@PathVariable Long orderId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Order order = orderService.getOrderById(orderId);
            model.addAttribute("order", order);
            return "order-confirmation";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Order not found.");
            return "redirect:/";
        }
    }
}