package com.techelevator.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Comment;

public class CommentSqlDAOTest extends DAOIntegrationTest {

	private CommentSqlDAO commentDao;
	private DataSource dataSource;
	private JdbcTemplate jdbc;

	@Before
	public void setup() {
		dataSource = this.getDataSource();
		jdbc = new JdbcTemplate(dataSource);
		commentDao = new CommentSqlDAO(jdbc);
	}

	@Test
	public void findAll() {
		List<Comment> allComBefore = commentDao.findAll();

		Comment temp = new Comment();
		commentDao.addComment(temp);

		List<Comment> allComAfter = commentDao.findAll();

		assertEquals(allComBefore.size() + 1, allComAfter.size());
	}

	@Test
	public void getAllCommentsByPhotoId() {
		List<Comment> allComByPhotoBefore = commentDao.getAllCommentsByPhotoId(4);
		Comment temp = new Comment();

		commentDao.addComment(temp);
		List<Comment> allComByPhotoAfter = commentDao.getAllCommentsByPhotoId(4);

		assertEquals(allComByPhotoBefore.size() + 1, allComByPhotoAfter.size());
	}

	@Test
	public void addComment() {
		List<Comment> allComBefore = commentDao.findAll();

		Comment temp = new Comment();
		commentDao.addComment(temp);

		List<Comment> allComAfter = commentDao.findAll();

		assertEquals(allComBefore.size() + 1, allComAfter.size());
	}

//	@Test
//	public void deleteComment() {
//		List<Comment> allComBefore = commentDao.findAll();
//
//		Comment temp = new Comment(2, 2);
//		commentDao.deleteComment(temp);
//
//		List<Comment> allComAfter = commentDao.findAll();
//
//		assertEquals(allComBefore.size() - 1, allComAfter.size());
//	}
}