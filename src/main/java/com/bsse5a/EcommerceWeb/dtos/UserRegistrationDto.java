package com.bsse5a.EcommerceWeb.dtos;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$",
            message = "Password must contain at least one special character and one number"
    )
    private String password;
}
