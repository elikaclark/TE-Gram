package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.Photo;
import com.techelevator.model.User;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/user/{id}" , method = RequestMethod.GET)
	User getUserByUserId(@PathVariable Long id){
		User result = userDAO.getUserById(id);
		return result;
	}
	
	@RequestMapping(path="/users/{id}", method = RequestMethod.PUT)
	public void updateUser(@Valid @RequestBody User user) {
		userDAO.updateUser(user);
	}

	@RequestMapping(path="/users/{id}", method = RequestMethod.PUT)
	public void updateUserPassword(@PathVariable Long id, @RequestBody String[] passwords) {
		userDAO.UpdateUserPassword(id, passwords);
	}
	
}
