package edu.neu.bloghub.service;

import java.util.ArrayList;

import edu.neu.bloghub.domain.ReceivedMessage;

public interface ReceivedMessageService {

	ArrayList<ReceivedMessage> listAll(Long id);

	void addMessage(ReceivedMessage receivedMessage);

}
