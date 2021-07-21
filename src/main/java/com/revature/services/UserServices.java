package com.revature.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		ArrayList<User> userList = (ArrayList<User>) ud.getUsers(); 
		for (User x : userList) {
			if (username.equals(x.getUsername())) {
				System.out.println("Username taken. Try again. ");
				return null;
			}
		}
		User u = new User(userList.size(), username, password, email);
		ud.writeToFile(u);
		return u;
	}
	
	public Order makeOrder(User user) {
		
		String protein;
		List<String> veggies = new ArrayList<String>();
		boolean spicy = false;
		
		System.out.println("What kind of protein would you like in your curry? You can only pick one.");
		mainloop1: while(true) {
			System.out.println("Your options are: " + Arrays.toString(Order.proteins));
			protein = scan.nextLine();
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
			Arrays.sort(Order.veggies);
			Arrays.sort(veg);
			vegloop: for (String x: veg) {
				if (Arrays.binarySearch(Order.veggies, x) < 0) {
					// Just reading the binarySearch documentation, 
					// it seems like a negative value is only returned 
					// when the entry is not found.
					System.out.println("Those options are not on our menu. Try again.");
					continue mainloop2;
				}
			}
			veggies = Arrays.asList(veg);
			break;
		}
		veggies.add(-1, protein); // Just finalizing the list of ingredients
		spicyloop: while(true) {
			System.out.println("Do you want your curry spicy? Y or N");
			String x = scan.nextLine();
			switch (x) {
			case "y":
			case "Y":
				spicy = true;
				break spicyloop;
			case "n":
			case "N":
				break spicyloop;
			default:
				// non-response entry
				System.out.println("That wasn't a valid response. Try again.");
			}
		}
		Order o = new Order(user, UserDAO.getOrders().size(), LocalDateTime.now(), veggies, spicy);
		return o;
	}
	
	public void completeOrder(Order o) {
		
	}
}
