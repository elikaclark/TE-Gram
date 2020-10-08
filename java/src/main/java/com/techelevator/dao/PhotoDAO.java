package com.techelevator.dao;



import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {
	
	public List<Photo> findAllPhotos();
	public void addPhoto(Photo photo);
	public void deletePhoto(int photo_id);
	public List<Photo>  getPhotoByUserId(int user_id);
	public Photo getProfilePic(int user_id);
	public Photo getPhotoById(int photo_id);
	public void editPhoto(Photo photo);

	
	
	
	

}
