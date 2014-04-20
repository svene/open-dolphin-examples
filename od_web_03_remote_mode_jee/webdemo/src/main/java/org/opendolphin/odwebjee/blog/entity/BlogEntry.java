package org.opendolphin.odwebjee.blog.entity;

import javax.persistence.Entity;

@Entity
public class BlogEntry {

	String title;

	public BlogEntry(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
