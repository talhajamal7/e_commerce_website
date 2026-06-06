package com.bsse5a.EcommerceWeb.mappers;

import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class UserMapper {

    private static PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity toEntity(UserRegistrationDto dto){
        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
    }
}
