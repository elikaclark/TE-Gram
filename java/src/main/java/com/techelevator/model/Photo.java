package com.techelevator.model;

import java.util.Date;

public class Photo {

	private int photo_id;
	private int user_id;
	private int likes;
	private String description;
	private String photo_src;
	// Date?
	private Date datetime;

	public Photo(){};

	public Photo (int photo_id, int user_id, int likes, String description, String photo_src) {
		this.photo_id = photo_id;
		this.user_id = user_id;
		this.likes = likes;
		this.description = description;
		this.photo_src = photo_src;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public String getDescription() {
		return description;
	}

	public String getPhoto_src() {
		return photo_src;
	}

	public int getLikes() {
		return likes;
	}

	public Date getTimestamp() {
		return datetime;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhoto_src(String photo_src) {
		this.photo_src = photo_src;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setTimestamp(Date datetime) {
		this.datetime = datetime;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
