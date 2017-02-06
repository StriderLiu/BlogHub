package edu.neu.bloghub.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.neu.bloghub.domain.Blog;
import edu.neu.bloghub.domain.Comment;
import edu.neu.bloghub.domain.User;
import edu.neu.bloghub.service.BlogService;
import edu.neu.bloghub.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/comment/{blogId}/add")
	public String addComment (@ModelAttribute("comment") @Valid Comment comment, 
			BindingResult bindingResult, @PathVariable Long blogId, 
			HttpSession session, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		Blog currentBlog = blogService.getBlog(blogId);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format(now);
		comment.setUser(currentUser);
		comment.setBlog(currentBlog);
		comment.setDate(time);
		
		currentBlog.addComment(comment);
		
		blogService.addBlog(currentBlog);
		commentService.addComment(comment);
		
		ArrayList<Blog> blogs = blogService.listAll();
		Iterator<Blog> iter = blogs.iterator();
		while (iter.hasNext()) {
			Blog blog = (Blog) iter.next();
			if (blog.getUser().getId() == currentUser.getId())
				iter.remove();
		}
		model.addAttribute("currentBlog", currentBlog);
		model.addAttribute("blogs", blogs);
		return "blogs_change";
	}
	
}
