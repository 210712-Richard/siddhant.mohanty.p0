package com.revature.services;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Order;
import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.util.SingletonScanner;

public class UserServices {

	private UserDAO ud = new UserDAO();
	private User u = new User();
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public User login(String name, String password) {
		try {
			User u = ud.getUser(name);
			if (password.equals(u.getPassword())) {
				// .equals() instead of == bc compared items are String objects
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
	
	public Order makeOrder(User user) {
		System.out.println("What kind of protein would you like in your curry? You can only pick one.");
		mainloop1: while(true) {
			System.out.println("Your options are: " + Arrays.toString(Order.proteins));
			String protein = scan.nextLine();
			proteinloop: for(String x : Order.proteins) {
				while(!protein.equals(x)) {
					continue proteinloop;
				}
				break mainloop1; // This line should only ever be reached if 'protein' matches an entry in Order.proteins.
			}
			System.out.println("We do not have " + protein + " in our menu. Try again.");
		}
		System.out.println("What kind of veggies would you like in your curry?");
		mainloop2: while(true) {
			System.out.println("Your options are: " + Arrays.toString(Order.veggies));
			System.out.println("Type out your selections with 1 space in between.");
			String[] veg = scan.nextLine().split(" ");
			List<String> veggies = Arrays.asList(veg);
			vegloop1: for (String y : veg) {
				vegloop2: for (String x : Order.veggies) {
					while(!x.equals(y)) {
						continue vegloop2; 
					}
					continue vegloop1;
				}
				break mainloop2;
			}
			System.out.println("Your veggies don't match what we have on our menu. Try again. ");
		}
		veggies.add(protein);
		
	}
}
