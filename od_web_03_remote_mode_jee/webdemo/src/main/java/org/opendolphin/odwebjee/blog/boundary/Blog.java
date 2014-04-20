package org.opendolphin.odwebjee.blog.boundary;

import org.opendolphin.odwebjee.blog.entity.BlogEntry;

import javax.ejb.Stateless;

@Stateless
public class Blog {

	public BlogEntry createBlog(String title) {
		return new BlogEntry(title);
	}

}
