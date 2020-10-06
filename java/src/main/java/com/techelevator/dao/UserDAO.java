package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    User findByEmail(String email);

    int findIdByUsername(String username);

    boolean create(String username, String password, String email, String role);
}
