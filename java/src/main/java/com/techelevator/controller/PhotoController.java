package com.techelevator.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.model.Photo;

@RestController
@CrossOrigin
public class PhotoController {
	
	@Autowired
	private PhotoDAO photoDAO;
	
	public PhotoController( PhotoDAO photoDAO) {
		this.photoDAO = photoDAO;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/photos" , method = RequestMethod.GET)
	public List<Photo> listOfPhotos(){
		List<Photo> allPhotos = photoDAO.findAllPhotos();
		return allPhotos;
	}
	
	@RequestMapping(path = "/photos/users/{id}", method = RequestMethod.GET)
	public List<Photo> returnPhotoByUserId(@PathVariable("id") int user_id ){
		List<Photo> allUsersPhotos = photoDAO.getPhotoByUserId(user_id);
		return allUsersPhotos;
		
	}
	
	@RequestMapping(path = "/photos/{photo_id}", method = RequestMethod.GET)
	public Photo returnPhotoById(@PathVariable("photo_id") int photo_id ){
		Photo allPhotos = photoDAO.getPhotoById(photo_id);
		
		return allPhotos;
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping (path = "/addPhoto" , method = RequestMethod.POST)
	public void  addNewPhoto(@RequestBody Photo newPhoto) {
			photoDAO.addPhoto(newPhoto);		
	}
	
	
	@RequestMapping(path="/editPhoto", method = RequestMethod.PUT)
	public void editPhoto(@RequestBody Photo photo) {
		photoDAO.editPhoto(photo);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/photos/{photo_id}", method = RequestMethod.DELETE)
	public void deletePhoto( @PathVariable int photo_id) {
		if(photoDAO.getPhotoById(photo_id) != null) {
			photoDAO.deletePhoto(photo_id);
		}
			
	}
	
	
	
}

