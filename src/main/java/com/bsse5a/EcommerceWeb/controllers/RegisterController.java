package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import com.bsse5a.EcommerceWeb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("user", new UserRegistrationDto());
        return "signup";
    }


    @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                   BindingResult bindingResult,
                                   Model model
                                   ){
            if(bindingResult.hasErrors()){
                return "signup";
            }
            System.out.println(userDto);
            try{
                userService.registerUser(userDto);
            }
            catch (Exception e){
                System.out.println("Error registering user!");
            }
            return "redirect:/login?registered";
        }
}
