package com.revature.beans;

import java.time.LocalDate;
import java.util.List;

public class User {
	private static final long serialVersionUID = -6426075925303078798L;
	private Integer id;
	private String username;
	private String email;
	private UserType type;
	private int behaviorScore; // Indicator of how friendly a user is
	private boolean banned; // Indicator of whether a user is banned or not. 0 behavior score is an auto-ban.
	
	public User() {
		super();
		this.type = UserType.CONSUMER;
		this.behaviorScore = 100;
		this.banned = false;
	}
	
	public User(Integer id, String username, String email) {
		this();
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public int getBehaviorScore() {
		return behaviorScore;
	}
	public void setBehaviorScore(int behaviorScore) {
		this.behaviorScore = behaviorScore;
	}
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (banned ? 1231 : 1237);
		result = prime * result + behaviorScore;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (banned != other.banned)
			return false;
		if (behaviorScore != other.behaviorScore)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type != other.type)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", type=" + type + ", behaviorScore="
				+ behaviorScore + ", banned=" + banned + "]";
	}
	
}
