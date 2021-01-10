package net.dev4any1;

import java.util.Date;

import net.dev4any1.pojo.Category;
import net.dev4any1.pojo.Journal;
import net.dev4any1.pojo.Publisher;
import net.dev4any1.pojo.Role;
import net.dev4any1.pojo.Subscription;
import net.dev4any1.pojo.User;


public class App 
{
    public static void main( String[] args )
    {
    	
    	User user = createUser(12l, Role.PUBLISHER);
		Publisher publisher = createPublisher(222l);
		Journal journal = createJournal(123l);
		Category category = createCategory(111l, "category1");
		Subscription subscription = createSubscription(33l);
		
		System.out.println(user);
		System.out.println(category);
		System.out.println(publisher);
		System.out.println(journal);
		System.out.println(subscription);
    	
       
    }
    
    public static User createUser (Long id, Role role) {
		User user = new User();
		user.setId(id);
		user.setRole(role.name());
		user.setLogin("mr." + id);
		user.setPassword("password");
		return user;
		}
	
	public static Category createCategory(Long id, String name) {
		Category category = new Category();
		category.setName(name);
		category.setId(id);
		return category;
	}
	
	public static Publisher createPublisher(Long id) {
		Publisher publisher = new Publisher();
		publisher.setId(id);
	    publisher.setName("mr.publisher" + id);
	    return publisher;
	}
	
	public static Journal createJournal(Long id) {
		Journal journal = new Journal();
		Date publishedAt = new Date();
		journal.setId(id);
		//journal.setPublishedAt(publishedAtSystem.currentTimeMillis());
		journal.setName("name");
		journal.setField("field");
		journal.setCategory(createCategory(null, null));
		journal.setPublisher(createPublisher(null));
		return journal;
	}
	
	public static Subscription createSubscription(Long id) {
		Subscription subscription = new Subscription();
		subscription.setId(id);
		//subscription.setCreatedAt(date);
		//subscription.setUser(user);
		subscription.setCategory(createCategory(null, null));
		return subscription;
	}  
    
}

