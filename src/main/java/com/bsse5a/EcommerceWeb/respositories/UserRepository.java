package com.bsse5a.EcommerceWeb.respositories;


import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRole(Role role);
}
