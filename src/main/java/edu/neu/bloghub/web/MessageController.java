package edu.neu.bloghub.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.bloghub.domain.ReceivedMessage;
import edu.neu.bloghub.domain.SentMessage;
import edu.neu.bloghub.domain.User;
import edu.neu.bloghub.service.ReceivedMessageService;
import edu.neu.bloghub.service.SentMessageService;
import edu.neu.bloghub.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private SentMessageService sentMessageService;
	
	@Autowired
	private ReceivedMessageService receivedMessageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String page (HttpSession session, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null) {
			ArrayList<ReceivedMessage> receivedMessages = receivedMessageService.listAll(currentUser.getId());
			model.addAttribute("messages", receivedMessages);
			model.addAttribute("newMessage", new SentMessage());
			return "message";
		} else {
			System.out.println("Please login first!");
			return "login";
		}
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendMessagse (@ModelAttribute("newMessage") @Valid SentMessage sentMessage,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "message";
		}
		User sender = (User)session.getAttribute("currentUser");
		User receiver = userService.getUser(sentMessage.getReceiver_name());
		
		sentMessage.setSender(sender);
		
		ReceivedMessage receivedMessage = new ReceivedMessage();
		receivedMessage.setContent(sentMessage.getContent());
		receivedMessage.setSubject(sentMessage.getSubject());
		receivedMessage.setReceiver(receiver);
		receivedMessage.setSender_name(sender.getName());
		
		sentMessageService.addMessage(sentMessage);
		receivedMessageService.addMessage(receivedMessage);
		
		receiver.addReceivedMessage(receivedMessage);
		sender.addSentMessage(sentMessage);	
		userService.updateUser(receiver);
		//userService.updateUser(sender);
		
		return "sendingSuccessful";
	}
}
