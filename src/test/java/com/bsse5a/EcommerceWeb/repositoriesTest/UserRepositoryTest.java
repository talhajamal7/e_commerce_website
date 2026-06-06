package com.bsse5a.EcommerceWeb.repositoriesTest;


import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

        @Test
        void testFindByEmail_UserExists() {
            UserEntity user = new UserEntity();
            user.setName("John Doe");
            user.setEmail("john@example.com");
            user.setPassword("password123");
            user.setRole(Role.USER);
            entityManager.persistAndFlush(user);
    
    
            Optional<UserEntity> found = userRepository.findByEmail("john@example.com");
    
            assertTrue(found.isPresent());
            assertEquals("John Doe", found.get().getName());
            assertEquals("john@example.com", found.get().getEmail());
        }

    @Test
    void testFindByEmail_UserDoesNotExist() {
        Optional<UserEntity> found = userRepository.findByEmail("nonexistent@example.com");

        assertFalse(found.isPresent());
    }

    @Test
    void testSaveUser() {
        UserEntity user = new UserEntity();
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setPassword("securepass");
        user.setRole(Role.ADMIN);

        UserEntity saved = userRepository.save(user);

        assertNotNull(saved.getId());
        assertEquals("Jane Doe", saved.getName());
        assertEquals("jane@example.com", saved.getEmail());
    }

    @Test
    void testFindById() {
        UserEntity user = new UserEntity();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("testpass");
        user.setRole(Role.USER);
        UserEntity saved = entityManager.persistAndFlush(user);

        Optional<UserEntity> found = userRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Test User", found.get().getName());
    }

    @Test
    void testDeleteUser() {
        UserEntity user = new UserEntity();
        user.setName("Delete Me");
        user.setEmail("delete@example.com");
        user.setPassword("pass");
        user.setRole(Role.USER);
        UserEntity saved = entityManager.persistAndFlush(user);

        userRepository.deleteById(saved.getId());
        Optional<UserEntity> found = userRepository.findById(saved.getId());

        assertFalse(found.isPresent());
    }
}
