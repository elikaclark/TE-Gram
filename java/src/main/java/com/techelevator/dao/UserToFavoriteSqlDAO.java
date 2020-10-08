package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Photo;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.UserToFavorite;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserToFavoriteSqlDAO implements UserToFavoriteDAO {

	private JdbcTemplate jdbcTemplate;

	public UserToFavoriteSqlDAO(JdbcTemplate jdbc){
		this.jdbcTemplate = jdbc;
	}


	@Override
	public List<UserToFavorite> getAllFavorites() {
		List<UserToFavorite> allFavorites = new ArrayList<>();
		String sql = "Select * from UserToFavorite ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

		while(result.next()){
			UserToFavorite fav = new UserToFavorite();
			fav.setUser_id(result.getLong("user_id"));
			fav.setPhoto_id((result.getLong("photo_id")));

			allFavorites.add(fav);
		}
		return allFavorites;
	}

	@Override
	public List<Photo> getAllUserFavorites(Long userId) {
		List<UserToFavorite> allFavoritesByUser = new ArrayList<>();
		List<Photo> userFavPhotos = new ArrayList<>();

		String sqlSelect = "Select * from UserToFavorite where user_id = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, userId);

		while(result.next()){
			UserToFavorite fav = new UserToFavorite();
			fav.setUser_id(result.getLong("user_id"));
			fav.setPhoto_id((result.getLong("photo_id")));

			allFavoritesByUser.add(fav);
		}


		PhotoSqlDAO photoDao = new PhotoSqlDAO(jdbcTemplate);
		allFavoritesByUser.forEach(UserToFavorite ->{
			Photo favPhoto =  photoDao.getPhotoById(UserToFavorite.getPhoto_id().intValue());
			userFavPhotos.add(favPhoto);
		});


		return userFavPhotos;
	}

	@Override
	public List<UserToFavorite> getAllFavoritesByPhotoId(Long photoId) {
		List<UserToFavorite> allFavoritesByPhotoId = new ArrayList<>();

		String sqlSelect = "Select * from UserToFavorite where photo_id = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, photoId);

		while(result.next()){
			UserToFavorite fav = new UserToFavorite();
			fav.setUser_id(result.getLong("user_id"));
			fav.setPhoto_id((result.getLong("photo_id")));

			allFavoritesByPhotoId.add(fav);
		}
		return allFavoritesByPhotoId;
	}

	@Override
	public boolean addFavorite(UserToFavorite userFavorite) {
		boolean added = false;

		String sqlInsert = "Insert into UserToFavorite (user_id, photo_id) values (?,?) ";
		added = jdbcTemplate.update(sqlInsert, userFavorite.getUser_id(), userFavorite.getPhoto_id()) == 1;

		return added;
	}

	@Override
	public boolean deleteFavorite(UserToFavorite userFavorite) {
		boolean deleted =  false;

		String sqlDelete = "Delete from UserToFavorite where user_id = ? and photo_id = ? ";
		deleted = jdbcTemplate.update(sqlDelete, userFavorite.getUser_id(), userFavorite.getPhoto_id()) == 1;

		return deleted;
	}
	
}
