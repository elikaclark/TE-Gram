package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Comment;

@Service
public class CommentSqlDAO implements CommentDAO {

	private JdbcTemplate jdbcTemplate;

	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Comment> findAll() {
		List<Comment> comments = new ArrayList<>();
		String sql = "SELECT * FROM comments";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Comment comment = mapRowToComment(results);
			comments.add(comment);
		}

		return comments;
	}

	@Override
	public boolean addComment(Comment comment) {
		boolean commentAdded = false;

		// add comment
		String insertComment = "INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES(?,?,?,?,current_timestamp)";

		commentAdded = jdbcTemplate.update(insertComment, comment.getLikes(), comment.getText(), comment.getPhoto_id(),
				comment.getUser_id()) == 1;

		return commentAdded;
	}

	@Override
	public void deleteComment(int commentId) {

		String sqlDelete = "DELETE FROM comments WHERE comment_id = ?";
		jdbcTemplate.update(sqlDelete, commentId);
	}

	@Override
	public List<Comment> getAllCommentsByPhotoId(int photoId) {
		List<Comment> allCommentsByPhotoId = new ArrayList<>();

		String sqlSelect = "SELECT * FROM comments WHERE photo_id = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, photoId);

		while (result.next()) {
			Comment comment = mapRowToComment(result);

			allCommentsByPhotoId.add(comment);
		}
		return allCommentsByPhotoId;
	}

	@Override
	public List<Comment> getAllCommentsByUserId(int userId) {
		List<Comment> allCommentsByUserId = new ArrayList<>();

		String sqlSelect = "SELECT * FROM comments WHERE user_id = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, userId);

		while (result.next()) {
			Comment comment = mapRowToComment(result);

			allCommentsByUserId.add(comment);
		}
		return allCommentsByUserId;
	}

	@Override
	public Comment getCommentById(int commentId) {
		String sql = "SELECT * FROM comments WHERE comment_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, commentId);
		if(results.next()) {
			return mapRowToComment(results);
		} else {
			throw new RuntimeException("commentId "+commentId+" was not found.");
		}
	}
	
	@Override
	public void editComment(Comment comment) {
		String sqlUpdComment = "UPDATE comments SET text = ?, likes = ? WHERE comment_id =?";
		jdbcTemplate.update(sqlUpdComment, comment.getText(), comment.getLikes(), comment.getComment_id() );
		
	}
	

	private Comment mapRowToComment(SqlRowSet rs) {
		Comment comment = new Comment();
		comment.setComment_id(rs.getInt("comment_id"));
		comment.setText(rs.getString("text"));
		comment.setLikes(rs.getInt("likes"));
		comment.setPhoto_id(rs.getInt("photo_id"));
		comment.setDatetime(rs.getDate("datetime"));
		comment.setUser_id(rs.getInt("user_id"));

		return comment;
	}
}
