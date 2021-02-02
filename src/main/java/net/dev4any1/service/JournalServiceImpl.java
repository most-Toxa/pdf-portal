package net.dev4any1.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.dev4any1.dao.JournalDao;
import net.dev4any1.dao.PublisherDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.JournalModel;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.model.SubscriptionModel;
import net.dev4any1.model.UserModel;

public class JournalServiceImpl implements JournalService {
	private UserDao userDao = new UserDao();
	private JournalDao journalDao = new JournalDao();
	private PublisherDao publisherDao = new PublisherDao();
	private UserService userService = new UserServiceImpl();

	@Override
	public List<JournalModel> listAll(UserModel user) {
		List<SubscriptionModel> subList = userService.getSubscription(user);
		Set<CategoryModel> catSet = new HashSet<CategoryModel>();
		for (SubscriptionModel sub : subList) {
			catSet.add((CategoryModel) sub.getCategory());
		}
		List<JournalModel> journalList = new ArrayList<JournalModel>();
		for (JournalModel journal : journalDao.getAll()) {
			if (catSet.contains(journal.getCategory())) {
				journalList.add(journal);
			}
		}
		return journalList;
	}

	@Override
	public List<JournalModel> publisherList(PublisherModel publisher) {
		List<JournalModel> journalList = new ArrayList<JournalModel>();
		for (JournalModel journal : journalDao.getAll()) {
			if (journal.getPublisher().equals(publisher)) {
				journalList.add(journal);
			}
		}
		return journalList;

	}

	@Override
	public JournalModel publish(PublisherModel publisher, JournalModel journal, Long categoryId) {
		
		return null;
	}

	@Override
	public void unPublish(PublisherModel publisher, Long journalId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<JournalModel> getNewByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
