package model.dao;

import model.entities.User;

public interface TelaLoginDao {

    User login(String username, String password);
    boolean cadastrar(User user);
    
}
