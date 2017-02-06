package edu.neu.bloghub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.bloghub.domain.SentMessage;

@Repository
public class SentMessageDAOImpl implements SentMessageDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addMessage(SentMessage sentMessage) {
		sessionFactory.getCurrentSession().saveOrUpdate(sentMessage);
		sessionFactory.getCurrentSession().flush();
	}
}
