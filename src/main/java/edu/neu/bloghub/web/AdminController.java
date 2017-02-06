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
import edu.neu.bloghub.service.UserService;

@Controller
@RequestMapping("/manager")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String manage (HttpSession session, Model model) {
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null) {
			ArrayList<User> users = userService.listAllUsers();
			
			Iterator<User> iter = users.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				if (user.getId() == currentUser.getId())
					iter.remove();
			}
			
			model.addAttribute("users", users);
			return "user_manage";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
	
	@RequestMapping(value = "/{userId}/view", method = RequestMethod.GET)
	public String viewProfile (@PathVariable Long userId, HttpSession session, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		return "viewProfile";
		
	}
	
	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
	public String deleteUser (@PathVariable Long userId, HttpSession session, Model model) {
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null) {
			//delete target user
			User targetUser = userService.getUser(userId);
			userService.deleteUser(targetUser);
			
			ArrayList<User> users = userService.listAllUsers();
			
			Iterator<User> iter = users.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				if (user.getId() == currentUser.getId())
					iter.remove();
			}
			
			model.addAttribute("users", users);
			return "user_manage";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
		
	}	
}
