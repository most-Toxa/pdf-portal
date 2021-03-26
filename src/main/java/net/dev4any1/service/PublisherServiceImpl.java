package net.dev4any1.service;

import javax.inject.Inject;

import net.dev4any1.dao.PublisherDao;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.pojo.Role;
import net.dev4any1.pojo.User;

public class PublisherServiceImpl implements PublisherService {
	@Inject
	private PublisherDao pubDao;

	@Override
	public PublisherModel createPublisher(String name, User user) {
		PublisherModel publisher = new PublisherModel();
		user.setRole(Role.PUBLISHER.name());
		publisher.setName(name);
		publisher.setUser(user);
		return pubDao.upsert(publisher); 
	}

}
