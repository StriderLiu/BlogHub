package edu.neu.bloghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.bloghub.dao.SentMessageDAO;
import edu.neu.bloghub.domain.SentMessage;

@Service
public class SentMessageServiceImpl implements SentMessageService {
	
	@Autowired
	private SentMessageDAO sentMessageDAO;

	@Override
	@Transactional
	public void addMessage(SentMessage sentMessage) {
		sentMessageDAO.addMessage(sentMessage);
	}
	
	
}
