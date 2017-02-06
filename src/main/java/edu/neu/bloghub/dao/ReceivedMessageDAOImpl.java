package edu.neu.bloghub.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.bloghub.domain.ReceivedMessage;

@Repository
public class ReceivedMessageDAOImpl implements ReceivedMessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<ReceivedMessage> listAll(Long id) {
		String hql = "from ReceivedMessage m where m.receiver.id='" + id + "'";
		return (ArrayList<ReceivedMessage>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public void addMessage(ReceivedMessage receivedMessage) {
		sessionFactory.getCurrentSession().saveOrUpdate(receivedMessage);
		sessionFactory.getCurrentSession().flush();
	}
}
