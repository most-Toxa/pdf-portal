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
import net.dev4any1.model.CategoryModel;
import net.dev4any1.service.CategoryService;
import net.dev4any1.service.CategoryServiceImpl;

public class CategoryServiceTest {

	private CategoryService service = new CategoryServiceImpl();

	@Test
	public void testCreateCategory() {
		CategoryModel cat = service.createCategory("name");
		Assert.assertNotNull(cat.getId());
		Assert.assertTrue(service.getAll().contains(cat));
	}

	protected Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		protected void configure() {
			bind(CategoryDao.class);
			bindScope(SessionScoped.class, Scopes.SINGLETON);
			bind(CategoryServiceImpl.class);
		}
	});

	@Before
	public void setup() {
		injector.injectMembers(service);
	}

}
