package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.security.CurrentUserDetails;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        String role = "ROLE_"+userEntity.getRole().name();

        return new CurrentUserDetails(userEntity);
    }
}
