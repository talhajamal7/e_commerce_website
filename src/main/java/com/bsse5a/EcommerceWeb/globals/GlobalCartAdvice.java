package com.bsse5a.EcommerceWeb.globals;

import com.bsse5a.EcommerceWeb.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalCartAdvice {

    @Autowired
    private CartService cartService;


    @ModelAttribute("cartItemCount")
    public int addCartCountToModel(HttpSession session) {
        try {
            return cartService.getCartItemCount(session);
        } catch (Exception e) {
            return 0;
        }
    }
}