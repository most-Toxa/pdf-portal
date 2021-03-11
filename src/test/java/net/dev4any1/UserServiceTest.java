package net.dev4any1;

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
import net.dev4any1.model.UserModel;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.UserServiceImpl;

public class UserServiceTest  {

	private UserServiceImpl service = new UserServiceImpl();
	
	@Test
	public void testCreateSubscriber() {
		UserModel user = service.createSubscriber("login", "password");
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user, service.getByLogin("login"));
	}
	
	@Test (expected = RuntimeException.class)
	public void testGetUnknownSubscriber() {
		 service.getByLogin("wrong login");
	}
	
	protected Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		protected void configure() {
			bindScope(SessionScoped.class, Scopes.SINGLETON);	
			bind(CategoryDao.class);
			bind(SubscriptionDao.class);
			bind(UserDao.class);
			bind(UserServiceImpl.class);
		}
	});

	@Before
	public void setup() {
		injector.injectMembers(service);
	}

}
