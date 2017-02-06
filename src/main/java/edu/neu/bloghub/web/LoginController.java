package edu.neu.bloghub.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.neu.bloghub.domain.User;
import edu.neu.bloghub.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loginControl", method = RequestMethod.GET)
	public String log (Model model) {
		model.addAttribute(new User());
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login (Model model, HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		if (user != null) {
			model.addAttribute("user",user);
			return "user";
		} else {
			model.addAttribute(new User());
			System.out.println("Login page is gonna be rendered.");
			return "login";
		}	
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout (HttpSession session, Model model) {
		if (session != null) {
			session = null;
		}
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register (Model model) {
		model.addAttribute(new User());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser (@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		userService.addUser(user);
		return "login";
	}
}
