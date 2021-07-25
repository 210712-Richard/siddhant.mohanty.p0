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
		// login
		app.post("/users", uc::login);
		// register
		app.put("/users/:username", uc::register);
		// logout
		app.delete("/users", uc::logout);
		// check behavior score
		app.get("/users/:username/score", uc::getBehaviorscore);
		// make an order
		app.post("/users/:username/order", uc::createOrder);
	}
	
}
