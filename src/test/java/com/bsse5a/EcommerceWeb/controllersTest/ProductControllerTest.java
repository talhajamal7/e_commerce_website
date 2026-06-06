package com.bsse5a.EcommerceWeb.controllersTest;

import com.bsse5a.EcommerceWeb.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testProductDashboardLoads() throws Exception{
        mockMvc.perform(get("/admin/dashboard/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product-dashboard"));
    }

    @Test
    @WithAnonymousUser
    void testShowProductsPageLoads() throws Exception{
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-products"));
    }
}
