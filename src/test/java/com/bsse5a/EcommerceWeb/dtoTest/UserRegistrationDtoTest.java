package com.bsse5a.EcommerceWeb.dtoTest;

import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserRegistrationDto() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("Password@123")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmptyName() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("")
                .email("john@example.com")
                .password("Password@123")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("must not be empty"));
    }

    @Test
    void testInvalidEmail() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("invalid-email")
                .password("Password@123")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("email"));
    }

    @Test
    void testEmptyEmail() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("")
                .password("Password@123")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testPasswordTooShort() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("Pass@1")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(2, violations.size());
        assertEquals("Password must be at least 8 characters long",
                violations.iterator().next().getMessage());
    }

    @Test
    void testPasswordWithoutSpecialCharacter() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("Password123")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Password must contain at least one special character and one number",
                violations.iterator().next().getMessage());
    }

    @Test
    void testPasswordWithoutNumber() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("Password@@@")
                .build();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Password must contain at least one special character and one number",
                violations.iterator().next().getMessage());
    }

    @Test
    void testAllFieldsNull() {
        UserRegistrationDto dto = new UserRegistrationDto();

        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(dto);
        assertEquals(2, violations.size());
    }

    @Test
    void testBuilderPattern() {
        UserRegistrationDto dto = UserRegistrationDto.builder()
                .name("John")
                .email("john@test.com")
                .password("Test@123")
                .build();

        assertEquals("John", dto.getName());
        assertEquals("john@test.com", dto.getEmail());
        assertEquals("Test@123", dto.getPassword());
    }


}
