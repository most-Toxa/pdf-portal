package net.dev4any1.service;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.model.CategoryModel;

@SessionScoped
public class CategoryServiceImpl implements CategoryService {
	@Inject
	private CategoryDao dao;

	public CategoryModel createCategory(String name) {
		CategoryModel cat = new CategoryModel();
		cat.setName(name);
		return dao.createAndGet(cat);
	}

	public Collection<CategoryModel> getAll() {
		return dao.getAll();
	}

}
