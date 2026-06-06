package com.bsse5a.EcommerceWeb.controllersTest;

import com.bsse5a.EcommerceWeb.controllers.StaticPagesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(StaticPagesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StaticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void testAboutUsPageLoads() throws Exception{
        mockMvc.perform(get("/aboutus"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-us"));
    }

    @Test
    @WithAnonymousUser
    void testContactUsPageLoads() throws Exception{
        mockMvc.perform(get("/contactus"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact-us"));
    }

    @Test
    @WithAnonymousUser
    void testTermsAndServicesPageLoads() throws Exception{
        mockMvc.perform(get("/terms-and-services"))
                .andExpect(status().isOk())
                .andExpect(view().name("terms-and-services"));
    }

    @Test
    @WithAnonymousUser
    void testWarrantyPolicyPageLoads() throws Exception{
        mockMvc.perform(get("/warranty-policy"))
                .andExpect(status().isOk())
                .andExpect(view().name("warranty-policy"));
    }

    @Test
    @WithAnonymousUser
    void testShippingInfoPageLoads() throws Exception{
        mockMvc.perform(get("/shipping-info"))
                .andExpect(status().isOk())
                .andExpect(view().name("shipping-info"));
    }

    @Test
    @WithAnonymousUser
    void testReturnAndRefundPageLoads() throws Exception{
        mockMvc.perform(get("/return-and-refund"))
                .andExpect(status().isOk())
                .andExpect(view().name("return-and-refund"));
    }
}
