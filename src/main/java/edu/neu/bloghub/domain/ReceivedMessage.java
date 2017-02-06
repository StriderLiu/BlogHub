package edu.neu.bloghub.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "RECEIVED_MESSAGE")
public class ReceivedMessage {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "Subject cannot be blank!")
	private String subject;

	@NotEmpty(message = "Message cannot be blank!")
	private String content;

	private String sender_name;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "RECEIVER_ID")
	private User receiver;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
}
