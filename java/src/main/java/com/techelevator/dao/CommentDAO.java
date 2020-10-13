package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comment;

public interface CommentDAO {

	List<Comment> findAll();
	
	public boolean addComment(Comment comment);
	
	public void deleteComment(int commentId);
	
	public List<Comment> getAllCommentsByPhotoId(int photoId);

	public List<Comment> getAllCommentsByUserId(int userId);
	
	public Comment getCommentById(int commentId);
	
	public void editComment(Comment comment);
 }
