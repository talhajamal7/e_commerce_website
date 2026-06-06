package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import com.bsse5a.EcommerceWeb.mappers.UserMapper;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void  registerUser(UserRegistrationDto userDto){
        UserEntity newUserEntity = userMapper.toEntity(userDto);
        userRepository.save(newUserEntity);
    }

    public List<UserEntity> getAllUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public long countUsersByRole(Role role) {
        return userRepository.findByRole(role).size();
    }

}
