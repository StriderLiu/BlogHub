package edu.neu.bloghub.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.bloghub.domain.Blog;

@Repository
public class BlogDAOHibeImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addBlog(Blog blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public Blog getBlog(Long blogId) {
		return (Blog) sessionFactory.getCurrentSession().load(Blog.class, blogId);
	}

	@Override
	public ArrayList<Blog> listAll() {
		ArrayList<Blog> blogs = (ArrayList<Blog>) sessionFactory.getCurrentSession().
								createQuery("from Blog").list();
		return blogs;
	}

}
