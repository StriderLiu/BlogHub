package edu.neu.bloghub.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.neu.bloghub.domain.Blog;
import edu.neu.bloghub.domain.User;
import edu.neu.bloghub.service.BlogService;
import edu.neu.bloghub.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String page (HttpSession session, Model model) {
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			model.addAttribute(user);
			if (user.getName().equals("admin")) {
				return "user_admin";
			} else {
				return "user";
			}		
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login (Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		User currentUser = userService.getUser(username);
		session.setAttribute("currentUser", currentUser);
//		User currentUser = userService.getUserAuthentSeg(user.getName(), user.getPassword());
		if (currentUser == null) {
			System.out.println("Either the User Name or the Password is wrong");
			return "login";
		}
		model.addAttribute(currentUser);
		System.out.println("Logged in User: " + currentUser.getName());
		if (username.equals("admin")) {
			return "user_admin";
		} else {
			return "user";
		}
			
	}
	
	@RequestMapping(value="/{userId}/photo", method=RequestMethod.GET)
	public @ResponseBody String viewPhotoById (@PathVariable Long userId, HttpServletResponse response)
			throws IOException {
		User user = userService.getUser(userId);
		byte[] photoBytes = user.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + 
						user.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
	
	@RequestMapping(value="/{userName}/pic", method=RequestMethod.GET)
	public @ResponseBody String viewPhotoByName (@PathVariable String userName, HttpServletResponse response)
			throws IOException {
		User user = userService.getUser(userName);
		byte[] photoBytes = user.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + 
						user.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
	
	@RequestMapping(value = "/{userId}/editProfile", method = RequestMethod.GET)
	public String editProfile (@PathVariable Long userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "editProfile";
	}
	
	@RequestMapping(value = "/{userId}/updateProfile", method = RequestMethod.POST)
	public String updateProfile (@PathVariable Long userId, 
			@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "editProfile";
		}
		user.setId(userId);
		userService.updateUser(user);
		model.addAttribute("user", user);
		return "user";
	}
	
	@RequestMapping(value = "/{userId}/writeBlog", method = RequestMethod.GET)
	public String writeBlog (@PathVariable Long userId, Model model) {
		User user = userService.getUser(userId);
		Blog blog = new Blog();
		model.addAttribute(user);
		model.addAttribute(blog);
		return "blogEditor";
	}
	
	@RequestMapping(value = "/{userId}/newBlog", method = RequestMethod.POST)
	public String addBlog (@PathVariable Long userId, 
			@Valid Blog blog, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			//return "redirect:/blogEditor";
		}
		User user = userService.getUser(userId);
		user.addBlog(blog);
		blog.setUser(user);
		userService.addUser(user);
		blogService.addBlog(blog);
		model.addAttribute(user);
		return "user";
	}
}
