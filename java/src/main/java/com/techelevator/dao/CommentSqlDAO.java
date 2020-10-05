package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
	        while(results.next()) {
	            Comment comment = mapRowToComment(results);
	            comments.add(comment);
	        }

	        return comments;
	    }
	    
	    public boolean addComment(String comment, int photoId, int userId) {
	    	 boolean commentAdded = false;

	         // add comment
	         String insertComment = "insert into comments (likes, text, photo_id, timestamp) values(?,?,?,?)";

	         GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	         String id_column = "comment_id";
	         commentAdded = jdbcTemplate.update(con -> {
	                     PreparedStatement ps = con.prepareStatement(insertComment, new String[]{id_column});
	                    
	                     return ps;
	                 }
	                 , keyHolder) == 1;
	         int newCommentId = (int) keyHolder.getKeys().get(id_column);

	         return commentAdded;
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
