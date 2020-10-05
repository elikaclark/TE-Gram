package com.techelevator.model;

import java.util.Date;

public class Comment {
	
	private int user_id;
	private int comment_id;
	private int photo_id;
	private int likes;
	private String text;
	// Date?
	private Date timestamp;
	
	public int getUser_id() {
		return user_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public int getPhoto_id() {
		return photo_id;
	}
	public int getLikes() {
		return likes;
	}
	public String getText() {
		return text;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
}
