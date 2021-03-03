package net.dev4any1;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.dao.JournalDao;
import net.dev4any1.dao.PublisherDao;
import net.dev4any1.dao.SubscriptionDao;
import net.dev4any1.dao.UserDao;
import net.dev4any1.resource.UserResource;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.JournalServiceImpl;
import net.dev4any1.service.UserServiceImpl;

/**
 * Implementation of the @GuiceServletContextListener.
 * "Logical place to hold the Dependency Injector" (c) Guice Team.
 * 
 * Defines the producers of the application resources.
 * 
 */

public class GuiceConfigContextListener extends GuiceServletContextListener {

	public static Injector injector = Guice.createInjector(new ServletModule() {
		@Override
		protected void configureServlets() {
			
			bind(UserDao.class);
			bind(UserServiceImpl.class);
			bind(CategoryDao.class);
			bind(CategoryServiceImpl.class);
			bind(JournalDao.class);
			bind(JournalServiceImpl.class);
			bind(PublisherDao.class);
			bind(SubscriptionDao.class);
			bind(UserResource.class);
		}
	});

	@Override
	protected Injector getInjector() {
		return injector;
	}
}
