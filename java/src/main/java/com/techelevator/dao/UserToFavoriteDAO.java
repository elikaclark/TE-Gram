package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.UserToFavorite;

public interface UserToFavoriteDAO {
	
	public abstract List<UserToFavorite> getAllUserFavorites();
	
	public void addFavorite(UserToFavorite userFavorite);
	
	public void deleteFavorite(UserToFavorite userFavorite);
	
}
