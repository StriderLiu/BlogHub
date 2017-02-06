package edu.neu.bloghub.service;

import java.util.ArrayList;
import java.util.List;

import edu.neu.bloghub.domain.User;

public interface UserService {
	public User getUserAuthentSeg(String name, String password);
	public void addUser(User user);
	public User getUser(Long userId);
	public List<User> listFriends(Long id);
	public User getUser(String receive_name);
	public void updateUser(User user);
	public void deleteFriend(User currentUser, User friend);
	public ArrayList<User> listAllUsers();
	public void deleteUser(User targetUser);
}
