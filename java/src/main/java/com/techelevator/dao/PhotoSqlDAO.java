package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Photo;

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
		 while(result.next()) {
	           Photo photo = mapRowToPhoto(result);
	            listOfPhotos.add(photo);
	        }

	        return listOfPhotos;
		}
	
	@Override
	public void addPhoto(Photo photo) {
		boolean photoAdded = false;
		String insertPhoto = "INSERT INTO photos ( description, photo_src, likes, datetime, user_id) values(?,?,?,?,?)";
		photoAdded = jdbcTemplate.update(insertPhoto, photo.getDescription(), photo.getPhoto_src(), photo.getLikes(), photo.getTimestamp(), photo.getUser_id()) == 1;
		
		
	}

	@Override
	public void deletePhoto(int photo_id) {
		boolean photoDeleted;
		String sqlDelete = "DELETE FROM photos where photo_id = ?";
		jdbcTemplate.update(sqlDelete, photo_id);
		
		// TODO Auto-generated method stub
	
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
