package com.fileupload.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
//int id;
//String photos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//
	@Column(name = "first_name")
	private String firstName;
//
	@Column(name = "last_name")
	private String lastName;
//
	@Column(name = "email")
	private String email;
//
	@Column(nullable = true, length = 64)
	private String photos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	
	
	

	public User(String firstName, String lastName, String email, String photos) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photos = photos;
	}

	public User() {
		super();
	}

	@Transient
	public String getPhotosImagePath() {
		if (photos == null || id == 0)
			return null;

		return "/user-photos/" + id + "/" + photos;
	}
	
	
	

}
