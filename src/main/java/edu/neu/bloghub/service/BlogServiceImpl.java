package edu.neu.bloghub.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.bloghub.dao.BlogDAO;
import edu.neu.bloghub.domain.Blog;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	
	@Override
	@Transactional
	public void addBlog(Blog blog) {
		blogDAO.addBlog(blog);
	}

	@Override
	@Transactional
	public Blog getBlog(Long blogId) {
		return blogDAO.getBlog(blogId);
	}

	@Override
	public ArrayList<Blog> listAll() {
		return blogDAO.listAll();
	}
	
}
