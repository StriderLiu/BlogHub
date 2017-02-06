package edu.neu.bloghub.dao;

import java.util.ArrayList;

import edu.neu.bloghub.domain.ReceivedMessage;

public interface ReceivedMessageDAO {

	ArrayList<ReceivedMessage> listAll(Long id);

	void addMessage(ReceivedMessage receivedMessage);

}
