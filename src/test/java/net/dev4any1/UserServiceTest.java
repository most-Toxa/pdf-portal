package net.dev4any1;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.SessionScoped;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.dao.SubscriptionDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.SubscriptionModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.UserServiceImpl;

public class UserServiceTest {

	private UserServiceImpl usService = new UserServiceImpl();
	private UserModel user; 
	private CategoryModel cat = new CategoryModel();
	private CategoryServiceImpl catService = new CategoryServiceImpl();

	@Test
	public void testCreateSubscriber() {
		UserModel user = usService.createSubscriber("login1", "password1");
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user, usService.getByLogin("login1").get());
	}

	protected Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		protected void configure() {
			bindScope(SessionScoped.class, Scopes.SINGLETON);
			bind(CategoryDao.class);
			bind(SubscriptionDao.class);
			bind(UserDao.class);
			bind(UserServiceImpl.class);
			bind(CategoryModel.class);
		}
	});

	@Before
	public void setup() {
		injector.injectMembers(usService);
		injector.injectMembers(catService);
		user = usService.createSubscriber("login", "password");
	}

	@Test
	public void testSubscribe() {
		CategoryModel cat = catService.createCategory("test");
		SubscriptionModel sub = usService.subscribe(user, cat.getId());
		Assert.assertEquals(user, sub.getUser());
		Assert.assertEquals(cat, sub.getCategory());
	}

	@Test(expected = Error.class)
	public void testSubscribeException() {
		SubscriptionModel sub = usService.subscribe(user, null); 
	}
	
	@Test(expected = Error.class)
	public void testGetByLoginException() {
	    usService.getByLogin("anton"); 
	}

	@Test
	public void testGetSubscription() {
		CategoryModel cat1 = catService.createCategory("test1");
		CategoryModel cat2 = catService.createCategory("test2");
		usService.subscribe(user, cat1.getId());
		usService.subscribe(user, cat2.getId());
		List<SubscriptionModel> subList = usService.getSubscription(user);
		for (SubscriptionModel sub : subList) {
			Assert.assertEquals(user, sub.getUser());
			Assert.assertTrue(usService.getSubscription(user).contains(sub));
		}
	}
}
