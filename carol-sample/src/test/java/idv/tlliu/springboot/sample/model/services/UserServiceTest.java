package idv.tlliu.springboot.sample.model.services;

//import static org.junit.Assert.assertEquals;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;

import idv.tlliu.springboot.carol.model.entity.User;
import idv.tlliu.springboot.carol.sample.model.common.exceptions.DuplicateInstanceException;
import idv.tlliu.springboot.carol.sample.model.common.exceptions.InstanceNotFoundException;
import idv.tlliu.springboot.carol.sample.model.services.UserService;

/**
 * The Class UserServiceTest.
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Creates the user.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	private User createUser(String userName) {
		return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com", "12345678", 1, 1);
	}

	/**
	 * Test sign up and login from id.
	 *
	 * @throws DuplicateInstanceException the duplicate instance exception
	 * @throws InstanceNotFoundException  the instance not found exception
	 */
	@Test
	public void testSignUpAndLoginFromId() throws DuplicateInstanceException, InstanceNotFoundException {

	  User user = createUser("user");

		userService.signUp(user);

		User loggedInUser = userService.loginFromId(user.getId());

		assertEquals(user, loggedInUser);
		assertEquals(1, user.getRole());

	}
}
