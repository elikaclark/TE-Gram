package com.techelevator.dao;

import com.techelevator.model.Photo;
import com.techelevator.model.UserToFavorite;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserToFavoriteSqlDAOTest extends DAOIntegrationTest{

    private UserToFavoriteSqlDAO userToFavDao;
    private DataSource dataSource;
    private JdbcTemplate jdbc;

    @Before
    public void setup(){
        dataSource = this.getDataSource();
        jdbc = new JdbcTemplate(dataSource);
        userToFavDao = new UserToFavoriteSqlDAO(jdbc);
    }
    @Test
    public void getAllFavorites() {
        List<UserToFavorite> allFavsBefore = userToFavDao.getAllFavorites();

        UserToFavorite temp = new UserToFavorite(1L, 1L);
        userToFavDao.addFavorite(temp);

        List<UserToFavorite> allFavsAfter = userToFavDao.getAllFavorites();

        assertEquals(allFavsBefore.size() + 1, allFavsAfter.size());
    }

    @Test
    public void getAllUserFavorites() {
        List<Photo> allFavByUserBefore = userToFavDao.getAllUserFavorites(3L);
        UserToFavorite temp = new UserToFavorite(3L, 1L);

        userToFavDao.addFavorite(temp);
        List<Photo> allFavsByUserAfter = userToFavDao.getAllUserFavorites(3L);

        assertEquals(allFavByUserBefore.size() + 1, allFavsByUserAfter.size());
    }

    @Test
    public void getAllFavoritesByPhotoId() {
        List<UserToFavorite> allFavByPhotoBefore = userToFavDao.getAllFavoritesByPhotoId(6L);
        UserToFavorite temp = new UserToFavorite(3L, 6L);

        userToFavDao.addFavorite(temp);
        List<UserToFavorite> allFavsByPhotoAfter = userToFavDao.getAllFavoritesByPhotoId(6L);

        assertEquals(allFavByPhotoBefore.size() + 1, allFavsByPhotoAfter.size());
    }

    @Test
    public void addFavorite() {
        List<UserToFavorite> allFavsBefore = userToFavDao.getAllFavorites();

        UserToFavorite temp = new UserToFavorite(1L, 1L);
        userToFavDao.addFavorite(temp);

        List<UserToFavorite> allFavsAfter = userToFavDao.getAllFavorites();

        assertEquals(allFavsBefore.size() + 1, allFavsAfter.size());
    }

    @Test
    public void deleteFavorite() {
        List<UserToFavorite> allFavsBefore = userToFavDao.getAllFavorites();

        UserToFavorite temp = new UserToFavorite(3L, 7L);
        userToFavDao.deleteFavorite(temp);

        List<UserToFavorite> allFavsAfter = userToFavDao.getAllFavorites();

        assertEquals(allFavsBefore.size() - 1, allFavsAfter.size());
    }
}