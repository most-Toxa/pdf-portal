package net.dev4any1.pojo;

import java.util.LinkedList;
import java.util.List;

public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", subscriptions="
				+ subscriptions + "]";
	}

	private Long id;
	private String login;
	private String password;
	private String role;
	private List<Subscription> subscriptions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Subscription> getSubscriptions(List<Subscription> subscribtions) {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
}