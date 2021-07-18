package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Order implements Serializable{
	
	private Integer id;
	private String name;
	private LocalDateTime time;
	private ArrayList<String> ingredients; 
	// Most Curries will be standardized to a similar size 
	// (meaning similar number of ingredients), hence an ArrayList.
	private Boolean spicy;
	
	public Order() {
		super();
		this.spicy = false;
	}
	
	public Order(Integer id, String name, LocalDateTime time, ArrayList<String> ingredients, Boolean spicy) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.ingredients = ingredients;
		this.spicy = spicy;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, ingredients, name, time);
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
				&& Objects.equals(name, other.name) && Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "Order [time=" + time + ", name=" + name + ", id=" + id + ", ingredients=" + ingredients + "]";
	}
}
