package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comment;

public interface CommentDAO {

	List<Comment> findAll();
	public boolean addComment(String comment, int photoId, int userId);
}
