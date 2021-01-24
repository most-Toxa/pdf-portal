package net.dev4any1.service;

import net.dev4any1.dao.UserDao;
import net.dev4any1.model.UserModel;
import net.dev4any1.pojo.Role;

public class UserService {

	private UserDao dao = new UserDao();

	public UserModel createSubscriber(String login, String password) {
		UserModel user = new UserModel();
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(Role.SUBSCRIBER.name());
		return dao.createAndGet(user);
	}

	public UserModel getByLogin(String login) {
		for (UserModel object : dao.getAll()) {
			if (object.getLogin().equals(login)) {
				return object;
			}
		}

		throw new RuntimeException("user with login " + login + " was not found");
	}
}
