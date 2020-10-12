package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.PhotoToLikes;
import com.techelevator.model.UserToFavorite;

@Service
public class PhotoToLikesSqlDAO implements PhotoToLikesDAO {
	
	private JdbcTemplate jdbcTemplate;

	public PhotoToLikesSqlDAO(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<PhotoToLikes> getAllLikes() {
		List<PhotoToLikes> allLikes = new ArrayList<>();
		String sql = "SELECT * FROM photo_to_likes ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

		while(result.next()){
			PhotoToLikes like = new PhotoToLikes();
			like.setPhoto_id((result.getLong("photo_id")));
			like.setUser_id(result.getLong("user_id"));
			

			allLikes.add(like);
		}
		return allLikes;
	}
	
	@Override
	public List<PhotoToLikes> getLikesByPhotoId(Long photo_id) {
		List<PhotoToLikes> allLikesByPhotoId = new ArrayList<>();

		String sqlSelect = "SELECT * from photo_to_likes WHERE photo_id = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, photo_id);

		while(result.next()){
			PhotoToLikes like = new PhotoToLikes();
			like.setPhoto_id((result.getLong("photo_id")));
			like.setUser_id(result.getLong("user_id"));
			

			allLikesByPhotoId.add(like);
		}
		return allLikesByPhotoId;
	}

	@Override
	public void addLike(PhotoToLikes like) {
		boolean added = false;
		String sql = "INSERT INTO photo_to_likes (photo_id, user_id) values(?,?)";
		added = jdbcTemplate.update(sql, like.getPhoto_id(), like.getUser_id() ) == 1 ;
		
	}

	@Override
	public boolean deleteLike(PhotoToLikes like) {
		boolean deleted = false;
		String sql = "DELETE FROM photo_to_likes WHERE user_id = ? AND photo_id = ?";
		deleted = jdbcTemplate.update(sql, like.getUser_id(),  like.getPhoto_id()) == 1 ;
		return deleted;
	}



	


	

}
