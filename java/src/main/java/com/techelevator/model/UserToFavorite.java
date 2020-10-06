package com.techelevator.model;

public class UserToFavorite {
	private Long user_id;
	private Long photo_id;


	public UserToFavorite() {
	}

	public UserToFavorite(Long user_id, Long photo_id) {
		this.user_id = user_id;
		this.photo_id = photo_id;
	}

	public Long getUser_id() {
		return user_id;
	}
	public Long getPhoto_id() {
		return photo_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public void setPhoto_id(Long photo_id) {
		this.photo_id = photo_id;
	}
	
	
}
