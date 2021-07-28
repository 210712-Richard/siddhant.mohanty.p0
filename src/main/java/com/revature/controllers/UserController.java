package com.revature.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Order;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.services.UserServices;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServices us = new UserServices();
	
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		// Try to use a JSON Marshaller to create an object of this type.
		// Javalin does not come with a JSON Marshaller but prefers Jackson. You could also use GSON
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		
		// Use the request data to obtain the data requested
		u = us.login(u.getUsername(), u.getPassword());
		log.debug(u);
		
		// Create a session if the login was successful
		if(u != null) {
			// Save the user object as loggedUser in the session
			ctx.sessionAttribute("loggedUser", u);
			
			// Try to use the JSON Marshaller to send a JSON string of this object back to the client
			ctx.json(u);
			return;
		}
		
		// Send a 401 is the login was not successful
		ctx.status(401);
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void register(Context ctx) {
		User u = ctx.bodyAsClass(User.class);

		User newUser = us.registerUser(u.getUsername(), u.getPassword(), u.getEmail());
		
		if (newUser.equals(null)) {
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken. ");
		}
		
//		if(us.checkAvailability(u.getUsername())) {
//			User newUser = us.register(u.getUsername(), u.getEmail(), u.getBirthday());
//			ctx.status(201);
//			ctx.json(newUser);
//		} else {
//			ctx.status(409);
//			ctx.html("Username already taken.");
//		}
	}
	
	public void createOrder(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		List<String> ingredients = Arrays.asList(ctx.pathParam("ingredients").split("_"));
		Boolean spicy = Boolean.parseBoolean(ctx.pathParam("spicy"));
		us.mkOrder(loggedUser, ingredients, spicy);
	}
	
	public void getBehaviorScore(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		ctx.json(loggedUser.getBehaviorScore());
	}
	
	public void checkNotifications(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		ctx.json(us.checkNotifications(loggedUser));
	}
	
	/**
	 * The methods that follow are to be used exclusively by admin users.
	 * As such, they all contain logic that immediately returns the method
	 * if the user is not an admin.
	 */
	
	public void changeBehaviorScore(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		Integer newBehaviorScore = Integer.parseInt(ctx.pathParam("newBehaviorScore"));
		if(loggedUser == null || !loggedUser.getUsername().equals(username) || !loggedUser.getType().equals(UserType.CREATOR)) {
			ctx.status(403);
			return;
		}
		String changeUsername = ctx.pathParam("changeuser");
		User changeUser = UserServices.checkUsers(loggedUser)
				.stream()
				.filter((user)->user.getUsername().equals(changeUsername))
				.findFirst()
				.orElse(null);
		if (changeUser == null) {
			ctx.status(404); // Couldn't find User
			return;
		}
		changeUser.setBehaviorScore(newBehaviorScore);
		ctx.json(changeUser);
	}
	
	public void completeOrder(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username) || !loggedUser.getType().equals(UserType.CREATOR)) {
			ctx.status(403);
			return;
		}
		String requester = ctx.pathParam("requester");
		List<Order> orders = UserServices.checkOrders(loggedUser);
		System.out.println(orders);
		Order order = UserServices.checkOrders(loggedUser)
				.stream()
				.filter(ord->{
					if(ord.getIssuer() == null)
						return false;
					return ord.getIssuer().equals(requester);
				})
				.findFirst()
				.orElse(null);
		us.completeOrder(order);
	}
	
	public void banUser(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username) || !loggedUser.getType().equals(UserType.CREATOR)) {
			ctx.status(403);
			return;
		}
		String bannedUsername = ctx.pathParam("bannedUser");
		User bannedUser = UserServices.checkUsers(loggedUser)
				.stream()
				.filter((user) -> user.getUsername().equals(bannedUsername))
				.findFirst()
				.orElse(null);
		if (bannedUser.equals(null)) {
			return;
		} else {
			us.banUser(bannedUser);
		}
	}
	
	public void unbanUser(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username) || !loggedUser.getType().equals(UserType.CREATOR)) {
			ctx.status(403);
			return;
		}
		String unbannedUsername = ctx.pathParam("unbannedUser");
		User unbannedUser = UserServices.checkUsers(loggedUser)
				.stream()
				.filter((user) -> user.getUsername().equals(unbannedUsername))
				.findFirst()
				.orElse(null);
		if (unbannedUser.equals(null)) {
			return;
		} else {
			us.unbanUser(unbannedUser);
		}
	}
	
	public void checkOrders(Context ctx) {
		// Can't check all the orders if we are not an admin
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username) || !loggedUser.getType().equals(UserType.CREATOR)) {
			ctx.status(403);
			return;
		}
		ctx.json(UserServices.checkOrders(loggedUser));
	}
	
}
