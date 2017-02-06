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
@Table(name="SENT_MESSAGE")
public class SentMessage {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Subject cannot be blank!")
	private String subject;

	@NotEmpty(message = "Content cannot be blank!")      
	private String content;
	
	@NotEmpty(message = "Receiver cannot be blank!")
	private String receiver_name;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "SENDER_ID")
	private User sender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
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
}
