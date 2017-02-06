package edu.neu.bloghub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.bloghub.dao.UserDAO;
import edu.neu.bloghub.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public User getUserAuthentSeg(String name, String password) {
		return userDAO.getUserAuthentSeg(name, password);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public User getUser(Long userId) {
		return userDAO.getUser(userId);
	}

	@Override
	@Transactional
	public List<User> listFriends(Long id) {
		return userDAO.listFriends(id);
	}

	@Override
	@Transactional
	public User getUser(String receive_name) {
		return userDAO.getUser(receive_name);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteFriend(User currentUser, User friend) {
		userDAO.deleteFriend(currentUser, friend);
	}

	@Override
	public ArrayList<User> listAllUsers() {
		return userDAO.listAllUsers();
	}

	@Override
	public void deleteUser(User targetUser) {
		userDAO.deleteUser(targetUser);
	}

}
