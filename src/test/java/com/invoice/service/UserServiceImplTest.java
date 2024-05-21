package com.invoice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.invoice.entity.User;
import com.invoice.exception.BlankNameException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.exception.UserNotFoundException;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setUserName("user1");
        user.setPassword("user123");
        user.setEmail("us@gmail.com");
    }

    @Test
    @DisplayName("testcase to save the user")
    @Disabled //to avoid save the same user multiple times if we want to test then change the user details in the setUp method..
    void testSaveUser() throws BlankNameException, UserAlreadyExistException {
        User savedUser = userService.saveUser(user);
        assertEquals(savedUser.getUserName(), user.getUserName());
        
    }

    @Test
    @DisplayName("testcase to check user is not saving with blankname")
    void testSaveUserWithBlankName() {
        user.setUserName("");
        assertThrows(BlankNameException.class, () -> userService.saveUser(user));
    }

    @Test
    @DisplayName("testcase to check save the user with existing details")
    void testSaveUserWithExistingUser() throws BlankNameException, UserAlreadyExistException {
        User existingUser = new User();
        existingUser.setUserName("user1");
        existingUser.setPassword("user123");
        //userService.saveUser(existingUser);
        assertThrows(UserAlreadyExistException.class, () -> userService.saveUser(user));
    }

    @Test
    @DisplayName("testcase to get userId based on username and password")
    void testGetUser() throws UserNotFoundException, BlankNameException, UserAlreadyExistException {
        Integer userId = userService.getUser(user.getUserName(), user.getPassword());
        assertThat(userId).isNotNull();
    }

    @Test
    @DisplayName("testcase to get userId with empty username")
    void testGetUserWithEmptyUsername() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser("", user.getPassword()));
    }

    @Test
    @DisplayName("testcase to get userId with empty password")
    void testGetUserWithEmptyPassword() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser(user.getUserName(), ""));
    }

    
    @Test
    @DisplayName("testcase to get userId with non existinguser ")
    void testGetUserWithNonExistingUser() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser("user1", "u123"));
    }

    
}