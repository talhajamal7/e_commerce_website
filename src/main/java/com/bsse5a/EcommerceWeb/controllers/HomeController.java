package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;

    public HomeController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String showIndex(Model model){
        List<ProductDto> products = productService.showAllProducts();
        model.addAttribute("products",products);
        return "index";
    }

    @GetMapping("/home")
    public String showHome(){
        return "redirect:/";
    }
}
