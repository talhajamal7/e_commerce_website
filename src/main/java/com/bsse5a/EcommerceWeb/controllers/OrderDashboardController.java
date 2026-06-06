package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.models.Order;
import com.bsse5a.EcommerceWeb.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/dashboard")
public class OrderDashboardController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String showOrdersDashboard(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders-dashboard";
    }

    @GetMapping("/orders/view/{id}")
    public String viewOrderDetails(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-detail";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("statuses", Order.OrderStatus.values());
        return "edit-order";
    }

    @PostMapping("/orders/update/{id}")
    public String updateOrder(@PathVariable Long id,
                              @RequestParam Order.OrderStatus status,
                              @RequestParam(required = false) String notes) {
        orderService.updateOrderStatus(id, status, notes);
        return "redirect:/admin/dashboard/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/dashboard/orders";
    }
}
