package edu.neu.bloghub.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="COMMENT")
public class Comment {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "comment cannot be blank!")
	private String content;
	
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "BLOG_ID", insertable = true, updatable = true)
	private Blog blog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
}
