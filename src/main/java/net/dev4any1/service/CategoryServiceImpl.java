package net.dev4any1.service;

import java.util.Collection;

import net.dev4any1.dao.CategoryDao;
import net.dev4any1.model.CategoryModel;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao dao = new CategoryDao();

	public CategoryModel createCategory(String name) {
		CategoryModel cat = new CategoryModel();
		cat.setName(name);
		return dao.createAndGet(cat);
	}

	public Collection<CategoryModel> getAll() {
		return dao.getAll();
	}

}
