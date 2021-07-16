package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserServices {

	private UserDAO ud = new UserDAO();
	
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
}
