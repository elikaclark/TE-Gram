package com.techelevator.dao;



import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {
	
	public List<Photo> findAllPhotos();
	public boolean addPhoto(Photo photo);
	public boolean deletePhoto(Photo photo);
	public List<Photo>  getPhotoByUserId(int user_id);
	
	
	
	

}
