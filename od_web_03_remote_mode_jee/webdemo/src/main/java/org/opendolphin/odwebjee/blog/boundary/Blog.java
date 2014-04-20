package org.opendolphin.odwebjee.blog.boundary;

import org.opendolphin.odwebjee.blog.entity.BlogEntry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class Blog {

	@PersistenceContext(name = "blog")
	EntityManager em;

	public BlogEntry createBlog(String title, String content, Date date) {
		BlogEntry blogEntry = new BlogEntry(title, content, date);
		em.persist(blogEntry);

		BlogEntry blogEntry1 = em.find(BlogEntry.class, blogEntry.getId());
		System.out.println("blogEntry1 = " + blogEntry1);
		return blogEntry1;
	}

}
