package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Order;
import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "users.dat";
	private static List<User> users;
	private static List<Order> orders;
	
	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		UserDAO.users = users;
	}
	
	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		UserDAO.orders = orders;
	}

	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		DataSerializer<Order> ds1 = new DataSerializer<Order>();
		orders = ds1.readObjectsFromFile("orders.dat");
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(users == null) {
			users = new ArrayList<User>();
			User u = new User(users.size(), "Creator_admin", "strongpassword", "creator_admin@curry.com");
			u.setType(UserType.CREATOR);
			users.add(u);
			ds.writeObjectsToFile(users, filename);
		}
		// doing the same thing as above, but for a list of orders instead of users
		if(orders == null) {
			orders = new ArrayList<Order>();
			Order o = new Order();
			orders.add(o);
			ds1.writeObjectsToFile(orders, "orders.dat");
		}
	}
	
	public User getUser(String username) {
		
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public void updateUser(User user) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		// I'll leave this method as a placeholder for our Week 3 Database integration.
	}
	
	public void writeToFile(User user) {
		// Adds a specified user into the users list and writes the list to a file.
		users.add(user);
		new DataSerializer<User>().writeObjectsToFile(users, filename);
	}
	
	public void writeOrderToFile(Order order) {
		// Adds a specified order into the orders list and writes the list to a file.
		orders.add(order);
		new DataSerializer<Order>().writeObjectsToFile(orders, "orders.dat");
	}
}
