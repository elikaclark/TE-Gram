package com.techelevator.model;

public class PhotoToLikes {
	
	private Long photo_id;
	private Long user_id;
	
	public PhotoToLikes() {
		
	}
	
public PhotoToLikes(Long photo_id, Long user_id) {
	this.user_id = user_id;
	this.photo_id = photo_id;
	
	}

public Long getPhoto_id() {
	return photo_id;
}

public void setPhoto_id(Long photo_id) {
	this.photo_id = photo_id;
}

public Long getUser_id() {
	return user_id;
}

public void setUser_id(Long user_id) {
	this.user_id = user_id;
}


}
