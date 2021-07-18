package com.revature.services;

import java.util.List;

import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserServices {

	private UserDAO ud = new UserDAO();
	private User u = new User();
	
	public User login(String name, String password) {
		try {
			User u = ud.getUser(name);
			if (password == u.getPassword()) {
				return u;
			} else {
				System.out.println("The password you have entered is not on file. Try again");
				return null;
			}
		} catch (Exception e) {
			System.out.println("That username is not associated with an account.");
			return null;
		} 
	}
	
	public User registerUser(String username, String password, String email) {
		// This function will generate a User with the 
		// specified attributes and populate users.dat with it.  
		List<User> userList = ud.getUsers(); 
		User u = new User(userList.size(), username, password, email);
		ud.writeToFile(u);
		return u;
	}
}
