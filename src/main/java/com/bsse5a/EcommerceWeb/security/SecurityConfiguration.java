package com.bsse5a.EcommerceWeb.security;

import com.bsse5a.EcommerceWeb.services.CurrentUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Autowired
    private CurrentUserDetailsService currentUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(currentUserDetailsService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/register",
                                "/products/**","/contactus","/warranty-policy",
                                "/terms-and-services","/shipping-info","/return-and-refund",
                                "/login","/home","/aboutus",
                                "/css/**","/images/**","/chat-socket/**"
                        ).permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers("/cart/**","/orders/**","/home/**")
                        .hasAnyRole("USER","ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/redirectingBasedOnRole", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(req ->
                                "GET".equals(req.getMethod()) && req.getRequestURI().equals("/logout")
                        )
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
