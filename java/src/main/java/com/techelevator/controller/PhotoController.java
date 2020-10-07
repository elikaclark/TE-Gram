package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.model.Photo;

@RestController

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
	
	@RequestMapping(path = "/photos/{id}", method = RequestMethod.GET)
	public List<Photo> returnPhotoByUserId(@PathVariable("id") int user_id ){
		List<Photo> allUsersPhotos = photoDAO.getPhotoByUserId(user_id);
		
		return allUsersPhotos;
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping (path = "/photos", method = RequestMethod.POST)
	public void  addNewPhoto(@RequestBody Photo newPhoto) {
		if (newPhoto != null) {
			photoDAO.addPhoto(newPhoto);
		}
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.DELETE)
	public void deletePhoto( @PathVariable int photo_id) {
			photoDAO.deletePhoto(photo_id);
	}
	
}

