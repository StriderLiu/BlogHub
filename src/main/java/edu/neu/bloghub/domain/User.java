package edu.neu.bloghub.domain;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@NotEmpty()
	@Size(min = 1, max = 25)
    //@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	//@Column(unique = true, name="name")
	private String name;

	@NotEmpty
	@Length(min = 6, max = 20)
	private String password;
	
	@Email(message = "Invalid email address, e.g. valid email address: example@gmail.com")
	private String email;
	
	@Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12, message = "Incorrect Format, valid e.g. 121212121212")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

	private String gender;
	
	private String country;
	
	private String city;
	
	private String address;
	
	@Transient
	private MultipartFile photo;

	@Lob private byte[] photoBytes;
	
	private String photoContentType; 
	
	private String photoFilename;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Blog> blogs = new HashSet<Blog>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comment> Comments = new HashSet<Comment>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SentMessage> sentMessages = new HashSet<SentMessage>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ReceivedMessage> receivedMessages = new HashSet<ReceivedMessage>();
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name="tbl_friends",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="friendId")
	)
	private Set<User> friends = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="tbl_friends",
	 joinColumns=@JoinColumn(name="friendId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	private Set<User> friendOf = new HashSet<User>();

	public Set<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Set<User> friendOf) {
		this.friendOf = friendOf;
	}

	public void setReceivedMessages(Set<ReceivedMessage> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public void addToFriends (User friend) {
		friends.add(friend);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
		
		setPhotoContentType(photo.getContentType());
		setPhotoFilename(photo.getOriginalFilename());
		try {
			setPhotoBytes(photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	
	public Set<ReceivedMessage> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessage(Set<ReceivedMessage> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Set<SentMessage> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Set<SentMessage> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Set<Comment> getComments() {
		return Comments;
	}

	public void setComments(Set<Comment> comments) {
		Comments = comments;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void addBlog(Blog blog) {
		blogs.add(blog);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", country=" + country + ", city=" + city + ", address="
				+ address + ", photo=" + photo + ", photoBytes=" + Arrays.toString(photoBytes) + ", photoContentType="
				+ photoContentType + ", photoFilename=" + photoFilename + ", blogs=" + blogs + ", Comments=" + Comments
				+ ", receivedMessages=" + receivedMessages + ", sentMessages=" + sentMessages + ", friends=" + friends
				+ "]";
	}

	public void addReceivedMessage(ReceivedMessage receivedMessage) {
		receivedMessages.add(receivedMessage);
	}

	public void addSentMessage(SentMessage sentMessage) {
		sentMessages.add(sentMessage);
	}

	public void removeFriend(User friend) {
		friends.remove(friend);
	}

	public void addToFriendOf(User user) {
		friendOf.add(user);
	}

}
