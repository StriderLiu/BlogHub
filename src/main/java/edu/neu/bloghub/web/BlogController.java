package edu.neu.bloghub.web;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.bloghub.domain.Blog;
import edu.neu.bloghub.domain.Comment;
import edu.neu.bloghub.domain.User;
import edu.neu.bloghub.service.BlogService;
import edu.neu.bloghub.service.UserService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String page (HttpSession session, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null) {
			ArrayList<Blog> blogs = blogService.listAll();
			
			Iterator<Blog> iter = blogs.iterator();
			while (iter.hasNext()) {
				Blog blog = (Blog) iter.next();
				if (blog.getUser().getId() == currentUser.getId())
					iter.remove();
			}
			
			model.addAttribute("blogs", blogs);
			model.addAttribute("comment", new Comment());
			return "blogs";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
	
	@RequestMapping(value = "/{blogId}/viewBlog", method = RequestMethod.GET)
	public String writeBlog (@PathVariable Long blogId, HttpSession session, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		if (currentUser != null) {
			ArrayList<Blog> blogs = blogService.listAll();
			
			Iterator<Blog> iter = blogs.iterator();
			while (iter.hasNext()) {
				Blog blog = (Blog) iter.next();
				if (blog.getUser().getId() == currentUser.getId())
					iter.remove();
			}
			
			model.addAttribute("blogs", blogs);
			model.addAttribute("comment", new Comment());
			Blog targetBlog = blogService.getBlog(blogId);
			model.addAttribute("currentBlog", targetBlog);
			return "blogs_change";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
}
