package com.invoice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.invoice.entity.User;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class UserRepositoryTestEx {
	
	@Autowired
	private UserRepository repo;
	
	private User user;
	
	@BeforeEach
    public void setUp() {
		user = User.builder()
				.userName("dravid")
				.password("k@123")
				.email("k@gmail.com")
				.build();
    }
	
	@Test
	@DisplayName("test case used to save the user")
	void testSaveUser() {
    	User savedUser = repo.save(user);
    	assertThat(savedUser.getUserId()).isPositive();
    			
	}
	
	@Test
    @DisplayName("Testing the user find based on username")
    void testFindByUserName() {
       
        User savedUser = repo.save(user);
        assertThat(repo.findByUserName(savedUser.getUserName())).isNotNull();

       
    }
	
	@Test
    @DisplayName("Testing the user find based on username and password")
   void testFindByUserNameAndPassword() {
        User savedUser = repo.save(user);
        String userName = savedUser.getUserName();
        String password = savedUser.getPassword();
        Integer result = repo.findByUserNameAndPassword(userName, password);
        assertThat(result).isNotNull();
    }
	
	@Test
	@DisplayName("testcase to save the user with balnk name")
	void testSaveUserWithBlankName() {
		User user1 = new User();
		user1.setUserName("");
		user1.setPassword("pswrd");
		user1.setEmail("jsdj@gmail.com");
		User savedUser= repo.save(user1);
		assertThat(savedUser).isNotNull();
		
	}
	
	@Test
	@DisplayName("testcase to save the user with balnk password")
	void testSaveUserWithBlankPassword() {
		User user1 = User.builder()
				.userName("dravid")
				.password("")
				.email("@gmail.com")
				.build();
		User savedUser = null;
		
		assertThat(savedUser).isNull();
		
	}
	
}
