package com.techelevator.controller;

import com.techelevator.dao.UserToFavoriteDAO;
import com.techelevator.model.Photo;
import com.techelevator.model.User;
import com.techelevator.model.UserToFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@CrossOrigin
public class UserToFavoritesController {

    @Autowired
    private UserToFavoriteDAO userToFavDao;

    public UserToFavoritesController(UserToFavoriteDAO favDao){
        this.userToFavDao = favDao;
    }

    @RequestMapping(path = "/allFavorites", method = RequestMethod.GET )
    public List<UserToFavorite> allFavorites(){
        List<UserToFavorite> allFavs = userToFavDao.getAllFavorites();
        return allFavs;
    }

    @RequestMapping(path = "/{id}/favorite", method = RequestMethod.GET)
    public List<Photo> getUserFavPhotos(@PathVariable("id") Long user_id){
        List<Photo> userFavs = userToFavDao.getAllUserFavorites(user_id);
        return userFavs;
    }

    @RequestMapping(path = "/favorite/photo/{id}", method = RequestMethod.GET)
    public List<User> getUserByFavPhoto(@PathVariable("id") Long photo_id){
        List<User> allUsersThatFavPhoto = userToFavDao.getAllUsersByPhotoId(photo_id);
        return allUsersThatFavPhoto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "addFavorite", method = RequestMethod.POST)
    public void addNewFavorite(@Valid @RequestBody UserToFavorite usrFav){
        List<Photo> thisUsersFavs = userToFavDao.getAllUserFavorites(usrFav.getUser_id());
        boolean existingFavorite = false;

        for(int i = 0; i < thisUsersFavs.size(); i++){
            if(thisUsersFavs.get(i).getPhoto_id() == usrFav.getPhoto_id()){
                existingFavorite = true;
                break;
            }
        }

        if(!existingFavorite) {
            boolean added = userToFavDao.addFavorite(usrFav);
            if (added) {
                System.out.println("Added new favorite photo to user_id: " + usrFav.getUser_id() + " with photo_id: " + usrFav.getPhoto_id());
            } else {
                System.out.println("Could not favorite photo to user_id: " + usrFav.getUser_id() + " with photo_id: " + usrFav.getPhoto_id());
            }
        }else{
            System.out.println("User_id " + usrFav.getUser_id() + " has already fav this photo_id " + usrFav.getPhoto_id());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "deleteFavorite", method = RequestMethod.DELETE)
    public void deleteFavorite(@Valid @RequestBody UserToFavorite favToDelete){
        boolean deleted = userToFavDao.deleteFavorite(favToDelete);
        if(deleted){
            System.out.println("Deleted favorite photo for user_id: " + favToDelete.getUser_id() + " with photo_id: " + favToDelete.getPhoto_id());
        }else{
            System.out.println("Could not delete favorite photo for user_id: " + favToDelete.getUser_id() + " with photo_id: " + favToDelete.getPhoto_id());
        }
    }
}
