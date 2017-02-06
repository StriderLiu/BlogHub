package edu.neu.bloghub.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BLOG")
public class Blog {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "Title cannot be blank!")
    //@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String title;
	
	@NotEmpty(message = "Content cannot be blank!")
	@Column(length = 10000)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = true, updatable = true)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<Comment>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
}
