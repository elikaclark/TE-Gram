package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Photo;
import com.techelevator.model.User;
import com.techelevator.model.UserToFavorite;

public interface UserToFavoriteDAO {

	List<UserToFavorite> getAllFavorites();
	List<UserToFavorite> getAllFavoritesByPhotoId(Long photo_id);
	List<Photo> getAllUserFavorites(Long userId);
	List<User> getAllUsersByPhotoId(Long photoId);
	boolean addFavorite(UserToFavorite userFavorite);
	boolean deleteFavorite(UserToFavorite userFavorite);
	
}
