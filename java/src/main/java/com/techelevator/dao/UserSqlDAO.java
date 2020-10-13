package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private PhotoSqlDAO photoSqlDAO;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.photoSqlDAO = new PhotoSqlDAO(this.jdbcTemplate);
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role, String name) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role,name) values(?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setString(4, name);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }
    
    @Override
    public void updateUser(User user) {
    	String sqlUpdUser = "UPDATE users SET username = ?, name=? WHERE user_id =?";
		jdbcTemplate.update(sqlUpdUser, user.getUsername(), user.getName(), user.getId());
		
    }

    @Override
    public void UpdateUserPassword(Long id, String[] passwords) {
        User temp = this.getUserById(id);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(passwords[0], temp.getPassword())) {
            try {
                String update = "Update users set password_hash = ? where user_id = ? ";
                String newPword = new BCryptPasswordEncoder().encode(passwords[1]);
                jdbcTemplate.update(update, newPword, id);
                System.out.println("password changed");

            }catch(Exception e){
                throw(e);
            }
        }else{
            System.out.println("could not change password");
        }
    }

    @Override
    public void deleteUser(Long id) {
        UserToFavoriteSqlDAO userToFav = new UserToFavoriteSqlDAO(this.jdbcTemplate);
        PhotoToLikesSqlDAO photoToLikes = new PhotoToLikesSqlDAO(this.jdbcTemplate);
        CommentSqlDAO commentDao = new CommentSqlDAO(this.jdbcTemplate);
        try{
            //Get all photos by user to delete
            List<Photo> allUserPhotos = this.photoSqlDAO.getPhotoByUserId(id.intValue());

            //For each photo will call the deletePhoto method
            //This will first delete all relation of that photo_id in the comments, userToFav, and userToLikes table
            //And then delete the photo from the photos table
            for(Photo userPhoto : allUserPhotos){
                this.photoSqlDAO.deletePhoto(userPhoto.getPhoto_id());
            }

            //Now need to retrieve all favorites, likes and comments made by the user
            List<Photo> allUserFav = userToFav.getAllUserFavorites(id);
            List<Photo> allUserLikes = photoToLikes.getAllUserLikes(id);
            List<Comment> allUserComments = commentDao.getAllCommentsByUserId(id.intValue());

            //deleting all rows in userToFav made by user
            for(Photo usrFavPhoto : allUserFav){
                long photoId = usrFavPhoto.getPhoto_id();
                UserToFavorite userFav = new UserToFavorite(id, photoId);
                userToFav.deleteFavorite(userFav);
            }

            //deleting all rows in photoToLikes made by user
            for(Photo userLikedPhoto : allUserLikes){
                long photoId = userLikedPhoto.getPhoto_id();
                PhotoToLikes userLike = new PhotoToLikes(photoId, id);
                photoToLikes.deleteLike(userLike);
            }

            //deleting all rows in comments by user
            for(Comment usrComment : allUserComments){
                commentDao.deleteComment(usrComment.getComment_id());
            }

            //now deleting the user after all foreign keys have been removed
            String delete = "Delete from users where user_id = ? ";
            this.jdbcTemplate.update(delete, id);
        }catch (Exception e){
            throw(e);
        }
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }

}
