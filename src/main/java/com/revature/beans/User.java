package com.revature.beans;

// import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class User implements Serializable{
	// private static final long serialVersionUID = -6426075925303078798L;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private UserType type;
	private int behaviorScore; // Indicator of how friendly a user is. A lower score is worse.
	private boolean banned; // Indicator of whether a user is banned or not. 0 behavior score is an auto-ban.
	private List<String> notifications; 
	
	public User() {
		super();
		this.type = UserType.CONSUMER;
		this.behaviorScore = 100;
		this.banned = false;
	}
	
	public User(Integer id, String username, String password, String email) {
		this();
		this.id = id;
		this.username = username;
		this.password = password;
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
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banned, behaviorScore, email, id, notifications, password, type, username);
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
		return banned == other.banned && behaviorScore == other.behaviorScore && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(notifications, other.notifications)
				&& Objects.equals(password, other.password) && type == other.type
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", type="
				+ type + ", behaviorScore=" + behaviorScore + ", banned=" + banned + ", notifications=" + notifications
				+ "]";
	}
	
}
