package com.bsse5a.EcommerceWeb.securityTest;

import com.bsse5a.EcommerceWeb.services.CurrentUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrentUserDetailsService currentUserDetailsService;

    @Test
    @WithAnonymousUser
    void testPublicEndpointsAccessible() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/register")).andExpect(status().isOk());
        mockMvc.perform(get("/login")).andExpect(status().isOk());
        mockMvc.perform(get("/products")).andExpect(status().isOk());
        mockMvc.perform(get("/contactus")).andExpect(status().isOk());
        mockMvc.perform(get("/aboutus")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void testAdminEndpointDeniedForAnonymous() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAdminEndpointDeniedForUser() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdminEndpointAccessibleForAdmin() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testHomeEndpointAccessibleForUser() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    @WithAnonymousUser
    void testHomeEndpointDeniedForAnonymous() throws Exception {
        mockMvc.perform(get("/home/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void testLoginPageAccessible() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testLogoutRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }

    @Test
    void testStaticResourcesAccessible() throws Exception {
        mockMvc.perform(get("/css/index-style.css"))
                .andExpect(status().isOk());
    }
}
