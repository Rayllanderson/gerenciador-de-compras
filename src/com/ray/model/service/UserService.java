package com.ray.model.service;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;

public class UserService {

    private UserDao dao;
    
    public UserService () {
	this.dao = DaoFactory.createUserDao();
    }
    
    public void updateSenhaOuNome(User user) {
	
	dao.update(user);
    }
    
    public void alterarUsername(User user) {
	//dao.alterarUsername(user);
    }
    
    public boolean verificarSenha(User user, String password) throws MyLoginException{
	/*if (dao.getSenhaUser(user).equals(password)) {
	    return true;
	}else {
	    throw new MyLoginException("Senha inválida.");
	}*/
	return false;
    }

    /*private boolean usernameExistente(String username) {
	User user;
	return false;
    }
    */

}
