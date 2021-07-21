package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable{
	
	private User issuer;
	private Integer id;
	// private String name; <-- unnecessary
	private LocalDateTime time;
	private List<String> ingredients; 
	// Most Curries will be standardized to a similar size 
	// (meaning similar number of ingredients), hence an ArrayList.
	private Boolean spicy;
	public static final String[] proteins = {"chicken", "tofu", "beef", "pork", "mutton"};
	public static final String[] veggies = {"carrots", "potatoes", "tomatoes", "cauliflower", "ginger", "garlic", "onions"};
	
	public Order() {
		super();
		this.spicy = false;
	}
	
	public Order(User issuer, Integer id, LocalDateTime time, List<String> ingredients, Boolean spicy) {
		this.issuer = issuer;
		this.id = id;
		// this.name = name;
		this.time = time;
		this.ingredients = ingredients;
		this.spicy = spicy;
	}
	
	
	public User getIssuer() {
		return issuer;
	}

	public void setIssuer(User issuer) {
		this.issuer = issuer;
	}

	public Boolean getSpicy() {
		return spicy;
	}

	public void setSpicy(Boolean spicy) {
		this.spicy = spicy;
	}

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isSpicy() {
		return spicy;
	}
	public void setSpicy(boolean spicy) {
		this.spicy = spicy;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, ingredients, issuer, spicy, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && Objects.equals(ingredients, other.ingredients)
				&& Objects.equals(issuer, other.issuer)
				&& Objects.equals(spicy, other.spicy) && Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "Order [issuer=" + issuer + ", id=" + id + ", time=" + time + ", ingredients="
				+ ingredients + ", spicy=" + spicy + "]";
	}
}
