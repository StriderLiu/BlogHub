package edu.neu.bloghub.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.bloghub.domain.*;
import edu.neu.bloghub.service.*;

@Controller
@RequestMapping("/friends")
public class FriendsController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String page (HttpSession session, Model model) {
		User user = (User) session.getAttribute("currentUser");
		if(user != null) {
			List<User> friends = userService.listFriends(user.getId());
			model.addAttribute("friends", friends);
			return "friends";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
	
	@RequestMapping(value = "{userId}/view", method = RequestMethod.GET)
	public String viewFriendProfile (@PathVariable Long userId,HttpSession session, Model model) {
			
		User user = (User) session.getAttribute("currentUser");
		if(user != null) {
			List<User> friends = userService.listFriends(user.getId());
			model.addAttribute("friends", friends);
			User targetFriend = userService.getUser(userId);
			model.addAttribute("currentFriend",targetFriend);
			return "friends_change";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
	
	@RequestMapping(value = "{userId}/add", method = RequestMethod.GET)
	public String addToFriends (@PathVariable Long userId, Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		User friend = userService.getUser(userId);
		
		currentUser.getFriends().add(friend);
		//friend.getFriends().add(currentUser);
		
		userService.updateUser(currentUser);
		//userService.addUser(friend);
		
		List<User> friends = userService.listFriends(currentUser.getId());
		model.addAttribute("friends", friends);
		return "friends";
	}
	
	@RequestMapping(value = "{userId}/remove", method = RequestMethod.GET)
	public String removeFriend (@PathVariable Long userId, Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		User friend = userService.getUser(userId);
		
		currentUser.getFriends().remove(friend);
		userService.deleteFriend(currentUser, friend);
		
		List<User> friends = userService.listFriends(currentUser.getId());
		model.addAttribute("friends", friends);
		return "friends";
	}
}
