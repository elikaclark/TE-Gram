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
		String sql = "select * from comments";

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
		String insertComment = "Insert into comments (likes, text, photo_id, timestamp) values(?,?,?,?)";

		commentAdded = jdbcTemplate.update(insertComment, comment.getLikes(), comment.getText(), comment.getPhoto_id(),
				comment.getTimestamp()) == 1;

		return commentAdded;
	}
	
	@Override
	public boolean deleteComment(Comment comment) {
		boolean commentDeleted = false;
		
		String sqlDelete = "Delete from comments where comment_id = ?";
		commentDeleted = jdbcTemplate.update(sqlDelete, comment.getComment_id()) == 1;
		return commentDeleted;
	}
	

	private Comment mapRowToComment(SqlRowSet rs) {
		Comment comment = new Comment();
		comment.setComment_id(rs.getInt("comment_id"));
		comment.setText(rs.getString("text"));
		comment.setLikes(rs.getInt("likes"));
		comment.setPhoto_id(rs.getInt("photo_id"));
		comment.setTimestamp(rs.getTimestamp("datetime"));

		return comment;
	}


}
