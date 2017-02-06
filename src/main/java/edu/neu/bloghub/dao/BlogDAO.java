package edu.neu.bloghub.dao;

import java.util.ArrayList;

import edu.neu.bloghub.domain.Blog;

public interface BlogDAO {

	void addBlog(Blog blog);

	Blog getBlog(Long blogId);

	ArrayList<Blog> listAll();

}
