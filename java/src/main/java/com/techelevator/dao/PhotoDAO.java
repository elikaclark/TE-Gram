package com.techelevator.dao;



import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {
	
	public List<Photo> findAllPhotos();
	public boolean addPhoto(Photo photo);
	public boolean deletePhoto(Photo photo);
	public Photo getPhotoById(int photo_id);
	public Photo getDescription(String description);
	public Photo getPhotoSrc( String photo_src);
	
	

}
