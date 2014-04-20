package org.opendolphin.odwebjee.blog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String title;
	String content;

	@Temporal(TemporalType.DATE)
	Date date;

	public BlogEntry() {
	}

	public BlogEntry(String title, String content, Date date) {
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "BlogEntry{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", date=" + date +
			'}';
	}
}
