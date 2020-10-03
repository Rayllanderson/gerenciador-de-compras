package com.ray.model.service;

import java.util.List;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;

public class UserService {

    private UserDao dao;

    public UserService() {
	this.dao = DaoFactory.createUserDao();
    }

    public boolean alterarUsername(Integer id, String newUsername) {
	User user = dao.findById(id);
	List<String> usernames = dao.findAllUsernames();
	if (user != null) {
	    // Verificando se o username atual não é igual ao username dele mesmo
	    if (!newUsername.equals(user.getUsername())) {
		for (String name : usernames) {
		    if (name.equals(newUsername)) {
			throw new MyLoginException("Username já existente!");
		    }
		}
		user.setUsername(newUsername);
		dao.update(user);
		return true;
	    }
	}
	return false;
    }

    public boolean alterarSenha(User user, String senhaAtual, String newPassword) {
	if (verificarSenha(user, senhaAtual)) { // verificar senha antes de poder alterar
	    user.setPassword(newPassword);
	    dao.update(user);
	    return true;
	} else {
	    throw new MyLoginException("Sua senha não corresponde a senha digitada. Digite novamente");
	}
    }

    public boolean verificarSenha(User user, String password) throws MyLoginException {
	if (user.getPassword().equals(password)) {
	    return true;
	}
	return false;
    }

    public void update(User user) {
	dao.update(user);
    }

    /*
     * private boolean usernameExistente(String username) { User user; return false;
     * }
     */

}
