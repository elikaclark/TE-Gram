package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.UserToFavorite;

public interface UserToFavoriteDAO {

	List<UserToFavorite> getAllFavorites();
	List<UserToFavorite> getAllUserFavorites(Long userId);
	List<UserToFavorite> getAllFavoritesByPhotoId(Long photoId);
	boolean addFavorite(UserToFavorite userFavorite);
	boolean deleteFavorite(UserToFavorite userFavorite);
	
}
