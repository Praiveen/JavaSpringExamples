package com.example.prak5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.prak5.services.UserService;
import com.example.prak5.entities.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(45);
        user.setName("John");
        user.setSurename("Doe");
        user.setAge(34);
        user.setCourse(3);

        User createdUser = userService.createUser(user);
        assertNotNull(createdUser.getId());
        assertEquals("John", createdUser.getName());
    }


    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(2L);
        user.setName("Alice");
        user.setSurename("Smith");
        user.setAge(28);
        user.setCourse(1);
        userService.createUser(user);

        User updatedUser = new User();
        updatedUser.setName("Alice Updated");
        updatedUser.setSurename("Smith Updated");
        updatedUser.setAge(29);
        updatedUser.setCourse(2);

        User result = userService.updateUser(2L, updatedUser);
        assertEquals("Alice Updated", result.getName());
        assertEquals(29, result.getAge());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(3L);
        user.setName("Bob");
        user.setSurename("Brown");
        user.setAge(22);
        user.setCourse(4);
        userService.createUser(user);

        userService.deleteUser(3L);
        Optional<User> deletedUser = userService.getUserById(3L);
        assertFalse(deletedUser.isPresent());
    }

    
}