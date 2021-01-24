package net.dev4any1.model;

import net.dev4any1.pojo.Category;

public class CategoryModel extends Category implements DbObject {
		private Long id;

		@Override
		public Long getId() {
			return id;
		}

		@Override
		public void setId(Long id) {
			this.id = id;	
		}

		
}
