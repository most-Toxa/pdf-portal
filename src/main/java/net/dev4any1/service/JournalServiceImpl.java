package net.dev4any1.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.dao.JournalDao;
import net.dev4any1.dao.PublisherDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.JournalModel;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.model.SubscriptionModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.pojo.Category;
import net.dev4any1.pojo.Journal;

@SessionScoped
public class JournalServiceImpl implements JournalService {
	@Inject
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setJournalDao(JournalDao journalDao) {
		this.journalDao = journalDao;
	}

	public void setPublisherDao(PublisherDao publisherDao) {
		this.publisherDao = publisherDao;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public void setCatDao(CategoryDao catDao) {
		this.catDao = catDao;
	}

	@Inject
	private JournalDao journalDao;
	@Inject
	private PublisherDao publisherDao;
	@Inject
	private UserServiceImpl userService;
	@Inject
	private CategoryDao catDao;

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
		Category cat = catDao.get(categoryId);
		if (cat == null) {
			throw new RuntimeException("unable to publish journal, category " + categoryId + " not found");
		}
		journal.setPublisher(publisher);
		journal.setCategory(cat);
		journalDao.update(journal); //need to change!!
		return journal;
	}

	@Override
	public void unPublish(PublisherModel publisher, Long journalId) {
		Journal journal = journalDao.get(journalId);
		if (journalDao.get(journalId) == null) {
			throw new RuntimeException("unable to unpublish journal, journal " + journalId + " not found");
		}
		if (!journal.getPublisher().getId().equals(publisher.getId())) {
			throw new RuntimeException("unable to unpublish journal, publisher  " + publisher.getName() + " is lier");
		}
		journalDao.delete(journalId);
	}

	@Override
	public List<JournalModel> getNewByCategory(Long categoryId) {
		// go to journalDao and create method getLastPosts (getAll 24h posts)
		
		// check categoryId exist
		if (catDao.get(categoryId) == null) {
			throw new RuntimeException("unable to add journal in category, category " + categoryId + " not exist");
		}
		
		List<JournalModel> journalList = new ArrayList<JournalModel>();
		Date date = new Date();
		for (JournalModel journal : journalDao.getAll()) {

			if (date.getTime() - journal.getPublishedAt().getTime() <= 24 * 60 * 60 * 1000) {
				journalList.add(journal);
			}
			// return all journals for past 24
		}
		return journalList;
	}
}
