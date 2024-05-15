package com.invoice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.invoice.entity.User;

@SpringBootTest
class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository; // Mocking the UserRepository interface

    @Test
    @DisplayName("Testing the user find based on username and password")
   void testFindByUserNameAndPassword() {
        String userName = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        
        when(userRepository.findByUserNameAndPassword(userName, password)).thenReturn(user);
        User result = userRepository.findByUserNameAndPassword(userName, password);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("Testing the user find based on username to check the unique user name")
    void testFindByUserName() {
        String userName = "testUser";
        User user = new User();
        user.setUserName(userName);
        when(userRepository.findByUserName(userName)).thenReturn(user);

        User result = userRepository.findByUserName(userName);

        assertEquals(user, result);
    }
    
    @Test
    @DisplayName("Testing saving the user")
    void testSaveUser() {
    	User user = new User();
    	user.setUserId(1);
    	user.setUserName("kohli");
    	user.setEmail("k@gmail.com");
    	when(userRepository.save(user)).thenReturn(user);
    	
    	User u = userRepository.save(user);
    	assertEquals(user, u);
    	
    	
    }
}
