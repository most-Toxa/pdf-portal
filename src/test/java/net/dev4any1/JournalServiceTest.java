package net.dev4any1;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.dao.JournalDao;
import net.dev4any1.dao.PublisherDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.JournalModel;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.JournalServiceImpl;
import net.dev4any1.service.PublisherServiceImpl;
import net.dev4any1.service.UserServiceImpl;

public class JournalServiceTest {

	private JournalServiceImpl service = new JournalServiceImpl();
	private CategoryServiceImpl catService = new CategoryServiceImpl();
	private PublisherServiceImpl pubService = new PublisherServiceImpl();
	private UserServiceImpl usService = new UserServiceImpl();
    private long categoryId;
    
	@Before
	public void init() {
		CategoryDao catDao = new CategoryDao();
		UserDao userDao = new UserDao();
		PublisherDao pubDao = new PublisherDao();
		JournalDao journalDao = new JournalDao();	
		service.setCatDao(catDao);
		service.setJournalDao(journalDao);
		service.setUserDao(userDao);
		service.setPublisherDao(pubDao);
		usService.setCatDao(catDao);
		usService.setUserDao(userDao);
		service.setUserService(usService);
		catService.setDao(catDao);
		pubService.setPubDao(pubDao);
		CategoryModel cat = catService.createCategory("test");
		categoryId = cat.getId();
		UserModel user = usService.createSubscriber("login", "password");
		PublisherModel publisher = pubService.createPublisher("toxa", user);
		JournalModel journal1 = createJournal(cat, 1l, publisher,
				new Date(System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)));
		JournalModel journal2 = createJournal(cat, 2l, publisher,
				new Date(System.currentTimeMillis() - (8 * 24 * 60 * 60 * 1000)));
		JournalModel journal3 = createJournal(cat, 3l, publisher,
				new Date(System.currentTimeMillis() - (12 * 60 * 60 * 1000)));
		service.publish(publisher, journal1, cat.getId());
		service.publish(publisher, journal2, cat.getId());
		service.publish(publisher, journal3, cat.getId());
	}

	private JournalModel createJournal(CategoryModel cat, long id, PublisherModel publisher, Date date) {
		JournalModel journal = new JournalModel();
		journal.withField("testfile").withCategory(cat).withId(id).withName("name").withPublisher(publisher)
				.withPublishedAt(date);
		return journal;
	}

	@Test
	public void testGetNewByCategory() {
		List<JournalModel> journalList = service.getNewByCategory(categoryId);
		Assert.assertTrue(journalList.size() == 1);
		Assert.assertTrue(journalList.get(0).getId() == 3l);
	}

}
