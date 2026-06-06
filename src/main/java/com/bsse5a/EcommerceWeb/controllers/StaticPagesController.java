package com.bsse5a.EcommerceWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPagesController {

    @GetMapping("/aboutus")
    public String showAboutUs(){
        return "about-us";
    }
    @GetMapping("/contactus")
    public String showContactUs(){
        return "contact-us";
    }
    @GetMapping("/terms-and-services")
    public String showTermsAndConditions(){
        return "terms-and-services";
    }
    @GetMapping("/warranty-policy")
    public String showWarrantyPolicy(){
        return "warranty-policy";
    }
    @GetMapping("/shipping-info")
    public String showShippingInfo(){
        return "shipping-info";
    }
    @GetMapping("/return-and-refund")
    public String showReturnAndRefund(){
        return "return-and-refund";
    }

}
