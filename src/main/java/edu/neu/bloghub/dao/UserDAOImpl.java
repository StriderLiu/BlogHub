package edu.neu.bloghub.dao;

import java.util.*;
import edu.neu.bloghub.domain.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUserAuthentSeg(String name, String password) {
		System.out.println("retrieving username & password.");
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
		for (User user : users)	{
			if ((user.getName().equals(name)) && (user.getPassword().equals(password)))
				return user;
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public User getUser(Long userId) {
		return (User)sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Override
	public List<User> listFriends(Long id) {
		List friends = sessionFactory.getCurrentSession().
				createQuery("from User u left join u.friends where u.id=:id").
				setParameter("id", id).list();
		List<User> friendList = new ArrayList<>();
		
		Iterator it = friends.iterator();
		while(it.hasNext()) {
			Object[] os = (Object[]) it.next();
			for (Object o : os) {
			User u = (User) o;
			friendList.add(u);
			}
		}
		
		//delete current user from the list
		Iterator<User> iter = friendList.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			if (user.getId() == id) {
				iter.remove();
			}
		}
			
		return friendList;
	}

	@Override
	public User getUser(String receive_name) {
		String hql = "from User u where u.name='" + receive_name + "'";//be careful with HQL
		return (User) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteFriend(User currentUser, User friend) {
//		sessionFactory.getCurrentSession().
//			createSQLQuery("delete from tbl_friends where userId=? and friendId=?").
//				setParameter(0, currentUser.getId()).setParameter(1, friend.getId());
		sessionFactory.getCurrentSession().
			createSQLQuery("delete from tbl_friends where userId='" + 
					currentUser.getId() + "' and friendId='" + friend.getId() + "'").executeUpdate();
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public ArrayList<User> listAllUsers() {
		return (ArrayList<User>) sessionFactory.getCurrentSession().
				createQuery("from User").list();
	}

	@Override
	public void deleteUser(User targetUser) {
		sessionFactory.getCurrentSession().delete(targetUser);
		sessionFactory.getCurrentSession().flush();
	}

}
