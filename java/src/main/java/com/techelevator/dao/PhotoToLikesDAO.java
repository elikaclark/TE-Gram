package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.PhotoToLikes;

public interface PhotoToLikesDAO {

	List<PhotoToLikes> getAllLikes();
	List<PhotoToLikes> getLikesByPhotoId(Long photo_id);
	public void addLike(PhotoToLikes like);
	boolean deleteLike(PhotoToLikes like);
	
	
	
}
