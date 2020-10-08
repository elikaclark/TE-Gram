package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Comment;
import com.techelevator.model.Photo;
import com.techelevator.model.UserToFavorite;

@Service
public class PhotoSqlDAO implements PhotoDAO {

	private JdbcTemplate jdbcTemplate;

	public PhotoSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Photo> findAllPhotos() {
		List<Photo> listOfPhotos = new ArrayList<Photo>();

		String sqlSelect = "select * from photos";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect);
		while (result.next()) {
			Photo photo = mapRowToPhoto(result);
			listOfPhotos.add(photo);
		}

		return listOfPhotos;
	}

	@Override
	public Photo getPhotoById(int photo_id) {
		
		String sql = "SELECT * FROM photos WHERE photo_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, photo_id);
		Photo photo = null;
		if(results.next()) {
			photo = mapRowToPhoto(results) ;
		}
		return photo;
	}
	
	@Override
	public List<Photo> getPhotoByUserId(int user_id) {
		List<Photo> listOfPhotos = new ArrayList<Photo>();
		String sql = "select * from photos where user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);

		while (results.next()) {
			Photo photo = mapRowToPhoto(results);
			listOfPhotos.add(photo);
		}
		return listOfPhotos;
	}

	
	@Override
	public void addPhoto(Photo photo) {
		boolean photoAdded = false;

		String insertPhoto = "INSERT INTO photos ( description, photo_src, likes, datetime, user_id) values(?,?,?,current_timestamp,?)";
		photoAdded = jdbcTemplate.update(insertPhoto, photo.getDescription(), photo.getPhoto_src(), photo.getLikes(), photo.getUser_id()) == 1;
		

	}
	
	@Override
	public void editPhoto(Photo photo) {
		String sqlUpdPhoto = "UPDATE photos SET description = ?, photo_src = ? WHERE photo_id =?";
		jdbcTemplate.update(sqlUpdPhoto, photo.getDescription(), photo.getPhoto_src(), photo.getPhoto_id() );
		
	}


	@Override
	public void deletePhoto(int photo_id) {
		boolean photoDeleted;
		long photoIdAsLong = photo_id;
		UserToFavoriteSqlDAO userToFavDao = new UserToFavoriteSqlDAO(jdbcTemplate);
		CommentSqlDAO commentDao = new CommentSqlDAO(jdbcTemplate);
		List<Comment> commentsByPhotoId = commentDao.getAllCommentsByPhotoId(photo_id);
		List<UserToFavorite> favoriteByPhotoId = userToFavDao.getAllFavoritesByPhotoId(photoIdAsLong);
		
		for (int i =0 ; i< favoriteByPhotoId.size(); i++) {
			try {
				userToFavDao.deleteFavorite(favoriteByPhotoId.get(i));
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		for (int i =0 ; i < commentsByPhotoId.size(); i++) {
			try {
				System.out.println(commentsByPhotoId.get(i));
				commentDao.deleteComment(commentsByPhotoId.get(i).getComment_id());
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		String sqlDelete = "DELETE FROM photos WHERE photo_id = ?";
		jdbcTemplate.update(sqlDelete, photo_id);
		
		// TODO Auto-generated method stub

	}

	

	@Override
	public Photo getProfilePic(int user_id) {

		return null;
	}


	
		private Photo mapRowToPhoto(SqlRowSet rs) {
        Photo photo = new Photo();
        photo.setPhoto_id(rs.getInt("photo_id"));
        photo.setDescription(rs.getString("description"));
        photo.setPhoto_src(rs.getString("photo_src"));
        photo.setLikes(rs.getInt("likes"));
        photo.setTimestamp(rs.getDate("datetime"));
        photo.setUser_id(rs.getInt("user_id"));
        return photo;
    }

		
		

}
