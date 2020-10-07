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

import com.techelevator.dao.CommentDAO;
import com.techelevator.model.Comment;

@RestController
public class CommentController {

	@Autowired
	private CommentDAO commentDAO;
	
	public CommentController(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	@RequestMapping(path = "/comments", method = RequestMethod.GET)
	public List<Comment> listComments() {
		
		List<Comment> allComments = commentDAO.findAll();
		return allComments;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addNewComment(@Valid @RequestBody Comment newComment) {
		commentDAO.addComment(newComment);
	}
	
    @RequestMapping(path = "/comments/{id}, method = RequestMethod.GET")
    public Comment returnCommentByCommentId(@PathVariable("id") int comment_id) {
    Comment commentById = commentDAO.getCommentById(comment_id);
    return commentById;
    }
    
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
	public void deleteExistingComment(@PathVariable int id) {
		if(commentDAO.getCommentById(id) != null) {
			commentDAO.deleteComment(id);
		}
	}
}
