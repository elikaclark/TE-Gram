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

import com.techelevator.dao.PhotoToLikesDAO;
import com.techelevator.model.PhotoToLikes;
import com.techelevator.model.User;

@RestController
@CrossOrigin
public class PhotoToLikesController {
	
	 @Autowired
	 private PhotoToLikesDAO photoToLikesDAO;
	 
	 public PhotoToLikesController(PhotoToLikesDAO photoToLikesDAO) {
		 this.photoToLikesDAO = photoToLikesDAO;
	 }
	 
	 @RequestMapping(path = "/likes/photo/{id}", method = RequestMethod.GET)
	    public List<PhotoToLikes> getLikeByPhotoId(@PathVariable("id") Long photo_id){
	        List<PhotoToLikes> allLikesByPhoto = photoToLikesDAO.getLikesByPhotoId(photo_id);
	        return allLikesByPhoto;
	    }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @RequestMapping (path = "/addLike" , method = RequestMethod.POST)
	 public void  addNewLike(@RequestBody PhotoToLikes like) { 
		 photoToLikesDAO.addLike(like);	
	}
	 
	 @ResponseStatus(HttpStatus.NO_CONTENT)
		@RequestMapping(path = "/deleteLike", method = RequestMethod.DELETE)
		public void deletePhoto( @PathVariable PhotoToLikes like) {
		 boolean deleted = photoToLikesDAO.deleteLike(like);
		 if(deleted){
	            System.out.println("Deleted like for photo_id: " + like.getPhoto_id() + " with user_id: " + like.getUser_id());
	        }else{
	            System.out.println("Could not remove like for photo_id: " + like.getPhoto_id() + " with user_id: " + like.getUser_id());
	        }
	    }
		
	 
	

}
