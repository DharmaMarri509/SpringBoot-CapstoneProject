package com.invoice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.invoice.entity.User;
import com.invoice.service.UserServiceImpl;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class UserRepositoryTestEx {
	
	private Logger log = LoggerFactory.getLogger(UserRepositoryTestEx.class);

	@MockBean
	private UserRepository repo;
	
	private User u;
	
	@BeforeEach
    public void setUp() {
		u = User.builder()
				.userName("dravid")
				.password("k@123")
				.email("k@gmail.com")
				.build();
    }
	
	@Test
	@DisplayName("test case used to save the user")
	void testSaveUser() {
		when(repo.save(u)).thenReturn(u);
    	
    	User user = repo.save(u);
    	assertEquals(u, user);		
	}
	
	@Test
    @DisplayName("Testing the user find based on username to check the unique user name")
    void testFindByUserName() {
        String userName = "testUser";
        User user = new User();
        user.setUserName(userName);
        when(repo.findByUserName(userName)).thenReturn(user);

        User result = repo.findByUserName(userName);

        assertEquals(user, result);
    }
	
	@Test
    @DisplayName("Testing the user find based on username and password")
   void testFindByUserNameAndPassword() {
        String userName = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        
        when(repo.findByUserNameAndPassword(userName, password)).thenReturn(user);
        User result = repo.findByUserNameAndPassword(userName, password);
        assertEquals(user, result);
    }
	
	@Test
	@DisplayName("testcase to save the user with balnk name")
	void testSaveUserWithBlankName() {
		User user = User.builder()
				.userName("")
				.password("k@123")
				.email("k@gmail.com")
				.build();
		User savedUser = null;
		
		//implement the business logic in service layer....
		if(user.getUserName().equals("")) {
			log.warn("the username should not be blank");
			
		}
		else {
			savedUser = repo.save(user);
		}
		assertThat(savedUser).isNull();
		
	}
	
	@Test
	@DisplayName("testcase to save the user with balnk password")
	void testSaveUserWithBlankPassword() {
		User user = User.builder()
				.userName("dravid")
				.password("")
				.email("@gmail.com")
				.build();
		User savedUser = null;
		if(user.getPassword().equals("")) {
			log.warn("the password should not be blank");
			
		}
		else {
			savedUser = repo.save(user);
		}
		assertThat(savedUser).isNull();
		
	}
	
}
