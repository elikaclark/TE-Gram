package com.techelevator.model;

import java.util.Date;

public class Comment {
	
	private int user_id;
	private int comment_id;
	private int photo_id;
	private int likes;
	private String text;
	// Date?
	private Date datetime;
	

	public Comment(){}
	
	public Comment(int comment_id, int likes, String text, int photo_id, int user_id) {
		super();
		this.comment_id = comment_id;
		this.likes = likes;
		this.text = text;
		this.photo_id = photo_id;
		this.user_id = user_id;
//		this.datetime =datetime;
	}
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
	public Date getDatetime() {
		return datetime;
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
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
