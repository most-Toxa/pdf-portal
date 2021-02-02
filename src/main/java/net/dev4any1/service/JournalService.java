package net.dev4any1.service;

import java.util.List;

import net.dev4any1.model.JournalModel;
import net.dev4any1.model.PublisherModel;
import net.dev4any1.model.UserModel;

public interface JournalService {

	List<JournalModel> listAll(UserModel user);// get all journals of user's subscription

	List<JournalModel> publisherList(PublisherModel publisher);// get all journals published by user

	JournalModel publish(PublisherModel publisher, JournalModel journal, Long categoryId);// creates new journal, notifies subscribers

	void unPublish(PublisherModel publisher, Long journalId);// deletes some journal
	
	List<JournalModel> getNewByCategory(Long categoryId);// get all new published journals for past 24 hours
}
