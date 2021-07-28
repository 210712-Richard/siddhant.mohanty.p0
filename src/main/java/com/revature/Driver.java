package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
//	public static void main(String[] args) {
//		Menu m = new Menu();
//		m.start();
//	}
	
	public static void main(String[] args) {
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
		
		app.get("/", (ctx)->ctx.html("Hello World"));
		
		// USER - login *
		app.post("/users", uc::login);
		
		// USER - register *
		app.put("/users/:username", uc::register);
		
		// USER - logout *
		app.delete("/users", uc::logout);
		
		// USER - check behavior score *
		app.get("/users/:username/score", uc::getBehaviorScore);
		
		// USER - check notifications 
		app.get("/users/:username/notifications", uc::checkNotifications);
		
		// USER - make an order
		app.post("/users/:username/order/:ingredients/:spicy", uc::createOrder);
		
		// ADMIN - change a users behavior score
		app.post("/users/:username/changescore/:changeuser/:newBehaviorScore", uc::changeBehaviorScore);
		
		// ADMIN - complete an order
		app.post("/users/:username/completeorder/:requester", uc::completeOrder);
		
		// ADMIN - ban a user
		app.post("/users/:username/ban/:bannedUser", uc::banUser);
		
		// ADMIN - unban a user
		app.post("/users/:username/unban/:unbannedUser", uc::unbanUser);
		
		// ADMIN - check pending orders
		app.post("/users/:username/checkorders", uc::checkOrders);
	}
	
}
