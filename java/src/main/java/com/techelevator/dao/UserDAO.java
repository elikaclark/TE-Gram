package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String name);
    
   public void updateUser(User user);

   public void UpdateUserPassword(Long id, String[]  passwords);

   public void deleteUser(Long id);
}
