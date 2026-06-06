package com.bsse5a.EcommerceWeb.configurationsTest;

import com.bsse5a.EcommerceWeb.configurations.DataInitializer;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class DataInitializerTest {
    @Test
    void testInitializeAdminCreatesNewAdmin() throws Exception {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        when(userRepository.findByEmail("hassam@admin.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("admin@123")).thenReturn("encodedPassword");

        DataInitializer initializer = new DataInitializer();
        CommandLineRunner runner = initializer.initializeAdmin(userRepository, passwordEncoder);

        runner.run();

        verify(userRepository).findByEmail("hassam@admin.com");
        verify(passwordEncoder).encode("admin@123");
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void testInitializeAdminSkipsIfAdminExists() throws Exception {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        UserEntity existingAdmin = new UserEntity();
        when(userRepository.findByEmail("hassam@admin.com")).thenReturn(Optional.of(existingAdmin));

        DataInitializer initializer = new DataInitializer();
        CommandLineRunner runner = initializer.initializeAdmin(userRepository, passwordEncoder);

        runner.run();

        verify(userRepository).findByEmail("hassam@admin.com");
        verify(userRepository, never()).save(any(UserEntity.class));
        verify(passwordEncoder, never()).encode(anyString());
    }
}
