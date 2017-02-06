package edu.neu.bloghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.bloghub.dao.CommentDAO;
import edu.neu.bloghub.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO commentDAO;

	@Override
	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
	}
}
