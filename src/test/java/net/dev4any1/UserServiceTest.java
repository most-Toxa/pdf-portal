package net.dev4any1;

import org.junit.Assert;
import org.junit.Test;

import net.dev4any1.model.UserModel;
import net.dev4any1.service.UserService;

public class UserServiceTest  {

	private UserService service = new UserService();
	
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
}
