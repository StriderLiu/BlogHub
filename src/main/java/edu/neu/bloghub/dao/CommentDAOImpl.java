package edu.neu.bloghub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.bloghub.domain.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addComment(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		sessionFactory.getCurrentSession().flush();
	}
}
