package net.dev4any1.dao;

import com.google.inject.Singleton;

import net.dev4any1.model.CategoryModel;

@Singleton
public class CategoryDao extends BaseDao<CategoryModel> {

	public CategoryModel getByName(String name) {
		for (CategoryModel cat : getAll()) {
			if (cat.getName().equals(name)) {
				return cat;
			}
		}
		return null;
	}  
}
