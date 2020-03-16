package com.travellerBlog.Traveller.Blog.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;

	@Column(name = "user_name", unique = true)
	private String name;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd-MM-yyyy")
	@Column(name = "post_time")
	private Date date = new Date();

	@Column(name = "description")
	private String description;

	@Transient
	private MultipartFile[] imageFile;

	@Column(name = "image_path")
	private String imagePath;

	public MultipartFile[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile[] imageFile) {
		this.imageFile = imageFile;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Post(String name, Date date, String description, MultipartFile[] imageFile, String imagePath) {
		this.name = name;
		this.date = date;
		this.description = description;
		this.imageFile = imageFile;
		this.imagePath = imagePath;
	}

	public Post() {
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", name=" + name + ", date=" + date + ", description=" + description
				+ ", imageFile=" + Arrays.toString(imageFile) + ", imagePath=" + imagePath + "]";
	}

}
