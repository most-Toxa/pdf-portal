package net.dev4any1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.dao.SubscriptionDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.SubscriptionModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.pojo.Role;

@SessionScoped
public class UserServiceImpl implements UserService {
	@Inject
	private UserDao userDao;
	@Inject
	private CategoryDao catDao;
	@Inject
	private SubscriptionDao subscripDao;

	public UserModel createSubscriber(String login, String password) {
		UserModel user = new UserModel();
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(Role.SUBSCRIBER.name());
		return userDao.createAndGet(user);
	}

	public UserModel getByLogin(String login) {
		for (UserModel object : userDao.getAll()) {
			if (object.getLogin().equals(login)) {
				return object;
			}
		}

		throw new RuntimeException("user with login " + login + " was not found");
	}

	@Override
	public SubscriptionModel subscribe(UserModel user, Long categoryId) {
		CategoryModel cat = catDao.get(categoryId);
		if (cat == null) {
			throw new RuntimeException("category with id " + categoryId + " was not found");
		} else {
			SubscriptionModel sub = new SubscriptionModel();
			sub.setCategory(cat);
			sub.setUser(user);
			sub.setCreatedAt(new Date());
			return subscripDao.createAndGet(sub);
		}
	}

	@Override
	public List<SubscriptionModel> getSubscription(UserModel user) {
		List<SubscriptionModel> subList = new ArrayList<SubscriptionModel>();
		for (SubscriptionModel sub: subscripDao.getAll()) {
			if (sub.getUser().equals(user)) {
				subList.add(sub);
			}
		}
		return subList;
	}
}
