package edu.neu.bloghub.service;

import java.util.ArrayList;

import edu.neu.bloghub.domain.Blog;

public interface BlogService {
	public void addBlog(Blog Blog);

	public Blog getBlog(Long blogId);

	public ArrayList<Blog> listAll();
}
