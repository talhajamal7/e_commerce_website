package com.bsse5a.EcommerceWeb.servicesTest;

import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import com.bsse5a.EcommerceWeb.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser_SuccessfulCreation() {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName("Test User");
        userDto.setEmail("test@example.com");
        userDto.setPassword("plainPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword123");

        userService.registerUser(userDto);

        verify(passwordEncoder).encode("plainPassword");

        ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userEntityCaptor.capture());

        UserEntity capturedUser = userEntityCaptor.getValue();

        assertEquals("Test User", capturedUser.getName());
        assertEquals("test@example.com", capturedUser.getEmail());
        assertEquals("encodedPassword123", capturedUser.getPassword(), "Password should be the encoded version, not plain text");
        assertEquals(Role.USER, capturedUser.getRole(), "Default role should be USER");
    }
}
