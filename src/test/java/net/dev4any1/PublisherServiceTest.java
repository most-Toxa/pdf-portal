package net.dev4any1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.SessionScoped;

import net.dev4any1.dao.PublisherDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.pojo.Role;
import net.dev4any1.service.PublisherServiceImpl;
import net.dev4any1.service.UserServiceImpl;


public class PublisherServiceTest {
    private PublisherServiceImpl pubService = new PublisherServiceImpl();
    private UserServiceImpl usService = new UserServiceImpl();
    
    protected Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		protected void configure() {
			bindScope(SessionScoped.class, Scopes.SINGLETON);
			bind(PublisherDao.class);
			bind(UserDao.class);
			bind(UserServiceImpl.class);
			
		}
	});

    @Before
	public void setup() {
		injector.injectMembers(pubService);
		injector.injectMembers(usService);
    }

    
	@Test
	public void testCreatePublisher() {
		UserModel user = usService.createSubscriber("login", "password");
		PublisherModel publisher = pubService.createPublisher("toxa", user);
		Assert.assertEquals(user.getRole(), Role.PUBLISHER.name());
		Assert.assertEquals("toxa", publisher.getName());
		Assert.assertEquals(user, publisher.getUser());;
	}
	
	@Test
	public void testGetPublisher() {
		UserModel user = usService.createSubscriber("login", "password");
		PublisherModel publisher = pubService.createPublisher("toxa", user);
		Assert.assertEquals(pubService.getPublisher(user), publisher);
	}
	
	@Test(expected = Error.class)
	public void testGetPublisherException() {
		UserModel user = usService.createSubscriber("login", "password");
		PublisherModel publisher = pubService.createPublisher("toxa", user);
		Assert.assertEquals(pubService.getPublisher(null), publisher);
	}
}
