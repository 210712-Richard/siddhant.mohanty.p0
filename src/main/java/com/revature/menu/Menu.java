package com.revature.menu;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.services.UserServices;
import com.revature.util.SingletonScanner;

public class Menu {
	
	private static final Logger Log = LogManager.getLogger(Menu.class); 	
	private UserServices us = new UserServices();
	private User loggedUser = new User();
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		Log.trace("Curry creation application has begun.");
		mainLoop: while(true) {
			switch(startMenu()) {
			case 1: 
				// user logs in
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				System.out.println("Please enter your password: ");
				String password = scan.nextLine();
				User u = us.login(username, password);
				if (u == null){
					Log.warn("Failed login");
				} else {
					loggedUser = u;
					System.out.println("Welcome back" + loggedUser.getUsername());
					Log.info("Login successful for " + loggedUser.getUsername());
					switch(loggedUser.getType()) {
					case CONSUMER:
						consumerMenu();
						break;
					case CREATOR:
						creatorMenu();
						break;
					}	
				}
				break;
			case 2:
				// user wants to register
				String email = verificationLoop("email");
				String usernameR = verificationLoop("username");
				String passwordR = verificationLoop("password");
				User registeredUser = us.registerUser(usernameR, passwordR, email);
				System.out.println("Welcome to the curry creator " + usernameR + "!");
				loggedUser = registeredUser;
				consumerMenu();
				Log.info("New User registered: " + loggedUser.toString());
				break;
			case 3:
				// user wants to quit the menu
				Log.trace("Curry creator terminated.");
				System.out.println("Goodbye!");
				break mainLoop;
			default:
				// entry is not 1, 2, or 3
				Log.warn("Invalid Menu selection.");
				System.out.println("Your entry is invalid. Press 1, 2 or 3 on your keyboard to pick an option.");
			}	
		}
	}
	
	private void creatorMenu() {
		// TODO Auto-generated method stub
		
	}

	private void consumerMenu() {
		// TODO Auto-generated method stub
		
	}

	private int startMenu() {
		Log.trace("called startMenu()");
		System.out.println("Welcome to the Curry Creator!");
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection = select();
		Log.trace("Start menu returning selection: "+selection);
		return selection;
	}
	
	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}
	
	private String verificationLoop(String thing) {
		while(true) {
			System.out.println("Please enter a " + thing + ":");
			String x0 = scan.nextLine();
			System.out.println("Please verify your" + thing +":");
			String x1 = scan.nextLine();
			if (x0.equals(x1)) {
				return x0;
			} else {
				System.out.println("Your " + thing + "s do not match. ");
			}
		}
	}
	
}
