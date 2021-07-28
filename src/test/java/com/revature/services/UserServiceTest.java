package com.revature.services;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserServiceTest {

	private static UserServices service;
	// private static UserDAO userdao;
	private static User u;
	
	
	@BeforeAll
	public static void setUpClass() {
		u = new User(10000, "Test", "testword", "test@test.com");
	}
	
	@BeforeEach
	public void setUpTests() {
		service = new UserServices();
		u.setBehaviorScore(100);
		u.setBanned(false);
		service.ud = Mockito.mock(UserDAO.class);
	}
	
	@Test
	public void testLoginBanned() {
		u.setBanned(true);
		assertNull("Assert that banned status prevents login by returning null user", 
				service.login(u.getUsername(), u.getPassword()));
	}
	
	@Test 
	public void testLoginWrongPassword() {
		String wrongpass = "This_will_never_be_a_password";
		assertNull("Assert that entering the incorrect password prevents login by returning null user, even with correct username", 
				service.login(u.getUsername(), wrongpass));
	}
	
	@Test
	public void testLoginNoUsername() {
		String noUsername = "This_will_never_be_a_username";
		assertNull("Assert that entering an unregistered username prevents login by returning a null user, no matter what the password is",
				service.login(noUsername, u.getPassword()));
	}
	
	@Test
	public void testRegisterTakenName() {
		String takenName = "jimothy";
		assertNull("Assert that trying to register an account with a taken username returns a null user",
				service.registerUser(takenName, "pass", "thisemail@doesnt.matter"));
	}
	
	@Test
	public void testOrderCheckerNotAdmin() {
		assertNull("Assert that trying to access list of pending orders as a regular user is prohibited",
				UserServices.checkOrders(u));
	}
}
