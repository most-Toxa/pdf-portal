package net.dev4any1;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import net.dev4any1.pojo.Category;
import net.dev4any1.pojo.Journal;
import net.dev4any1.pojo.Publisher;
import net.dev4any1.pojo.Role;
import net.dev4any1.pojo.Subscription;
import net.dev4any1.pojo.User;

public class AppTest {
	
	private User user ;
	private Publisher publisher ;
	private Journal journal;
	private Category category;
	private Subscription subscription;
	
	@Before
	public void initData() {

		user = createUser(12l, Role.PUBLISHER);
		publisher = createPublisher(222l);
		journal = createJournal(123l);
		category = createCategory(111l, "category1");
		subscription = createSubscription(33l);

		System.out.println(user);
		System.out.println(category);
		System.out.println(publisher);
		System.out.println(journal);
		System.out.println(subscription);

	}

	public static User createUser(Long id, Role role) {
		User user = new User();
		user.setId(id);
		user.setRole(role.name());
		user.setLogin("mr." + id);
		user.setPassword("password");
		return user;
	}

	public Category createCategory(Long id, String name) {
		Category category = new Category();
		category.setName(name);
		category.setId(id);
		return category;
	}

	public Publisher createPublisher(Long id) {
		Publisher publisher = new Publisher();
		publisher.setId(id);
		publisher.setName("mr.publisher" + id);
		return publisher;
	}

	public Journal createJournal(Long id) {
		Journal journal = new Journal();
		Date publishedAt = new Date();
		journal.setId(id);
		// journal.setPublishedAt(publishedAtSystem.currentTimeMillis());
		journal.setName("name");
		journal.setField("field");
		journal.setCategory(createCategory(null, null));
		journal.setPublisher(createPublisher(null));
		return journal;
	}

	public Subscription createSubscription(Long id) {
		Subscription subscription = new Subscription();
		subscription.setId(id);
		// subscription.setCreatedAt(date);
		// subscription.setUser(user);
		subscription.setCategory(createCategory(null, null));
		return subscription;
	}
	
	@Test
	public void firstTest() {
		Assert.assertNotNull(category);
	}

}
