package net.dev4any1.pojo;
import java.util.Date;

public class Journal {
	private Long id;
	private String name;
	private String field;
	private Date publishedAt;
	private Category category;
	private Publisher publisher;
	
	@Override
	public String toString() {
		return "Journal [id=" + id + ", name=" + name + ", field=" + field + ", publishedAt=" + publishedAt
				+ ", category=" + category + ", publisher=" + publisher + "]";
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public Date getPublishedAt() {
		return publishedAt;
	}
	
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
