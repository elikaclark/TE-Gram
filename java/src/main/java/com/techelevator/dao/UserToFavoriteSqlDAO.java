package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.UserToFavorite;

public class UserToFavoriteSqlDAO implements UserToFavoriteDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserToFavorite> getAllUserFavorites() {
		
		return null;
	}

	@Override
	public void addFavorite(UserToFavorite userFavorite) {
		
	}

	@Override
	public void deleteFavorite(UserToFavorite userFavorite) {
		// TODO Auto-generated method stub
		
	}
	
}
