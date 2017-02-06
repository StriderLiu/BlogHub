package edu.neu.bloghub.dao;

import java.util.ArrayList;
import java.util.List;

import edu.neu.bloghub.domain.User;

public interface UserDAO {
	User getUserAuthentSeg(String name, String password);
	void addUser(User user);
	User getUser(Long userId);
	List<User> listFriends(Long id);
	User getUser(String receive_name);
	void updateUser(User user);
	void deleteFriend(User currentUser, User friend);
	ArrayList<User> listAllUsers();
	void deleteUser(User targetUser);
}
