package com.bsse5a.EcommerceWeb.controllers;


import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.security.CurrentUserDetails;
import com.bsse5a.EcommerceWeb.services.OrderService;
import com.bsse5a.EcommerceWeb.services.ProductService;
import com.bsse5a.EcommerceWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String getAdminDashboard(Model model){
        model.addAttribute("productsCount", productService.allProductsCount());
        model.addAttribute("ordersCount", orderService.getTotalOrdersCount());
        model.addAttribute("usersCount", userService.countUsersByRole(Role.USER));
        model.addAttribute("topProducts", productService.getTopProducts(5));
        model.addAttribute("recentOrders", orderService.getRecentOrders(5));
        return "admin-dashboard";
    }
    @GetMapping("/admin/dashboard/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsersByRole(Role.USER));
        model.addAttribute("userCount", userService.countUsersByRole(Role.USER));
        return "users";
    }
}
