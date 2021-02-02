package net.dev4any1;

import org.junit.Assert;
import org.junit.Test;

import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.service.CategoryService;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.UserServiceImpl;

public class CategoryServiceTest  {

	private CategoryService service = new CategoryServiceImpl();
	
	@Test
	public void testCreateCategory() {
		CategoryModel cat = service.createCategory("name");
		Assert.assertNotNull(cat.getId());
		Assert.assertTrue(service.getAll().contains(cat));
	}
	

}
