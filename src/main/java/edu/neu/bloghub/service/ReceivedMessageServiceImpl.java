package edu.neu.bloghub.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.bloghub.dao.ReceivedMessageDAO;
import edu.neu.bloghub.domain.ReceivedMessage;

@Service
public class ReceivedMessageServiceImpl implements ReceivedMessageService {

	@Autowired
	private ReceivedMessageDAO receivedMessageDAO;

	@Override
	@Transactional
	public ArrayList<ReceivedMessage> listAll(Long id) {
		return receivedMessageDAO.listAll(id);
	}

	@Override
	@Transactional
	public void addMessage(ReceivedMessage receivedMessage) {
		receivedMessageDAO.addMessage(receivedMessage);
	}
	
}
