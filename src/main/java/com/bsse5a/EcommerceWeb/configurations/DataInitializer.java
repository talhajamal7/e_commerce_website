package com.bsse5a.EcommerceWeb.configurations;

import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import groovy.util.logging.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@lombok.extern.slf4j.Slf4j
@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByEmail("admin@fitmart.com").isEmpty()){
                UserEntity admin = new UserEntity();
                admin.setEmail("admin@fitmart.com");
                admin.setName("admin");
                admin.setPassword(passwordEncoder.encode("admin@123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                log.info("Admin created!");
            }else{
                log.info("Admin exists");
            }
        };
    }
}
