package com.bsse5a.EcommerceWeb.globals;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {
    @ModelAttribute("currentPath")
    public String currentPath(HttpServletRequest httpServletRequest){
        return httpServletRequest.getRequestURI();
    }
}
