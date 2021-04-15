package com.ray.model.dao;


import java.util.List;

import com.ray.model.entities.User;

public interface UserDao {
    /**
     * @param username that will be checked in database
     * @param password that will be checked in database
     *
     * @return return the current User with these username
     */
    User login(String username, String password);

    /**
     * @param user that will be registered
     *
     * @return return true if cadastre was sucefull
     */
    boolean cadastrar(User user);

    void update(User user);

    User findById(Long id);

    List<String> findAllUsernames();
}
