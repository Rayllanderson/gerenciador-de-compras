package model.dao;

import model.entities.User;

public interface TelaLoginDao {

    Integer login(String username, String password);
    boolean cadastrar(User user);
    
}
