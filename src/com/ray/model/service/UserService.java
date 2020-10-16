package com.ray.model.service;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.Arquivo;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;
import com.ray.model.validacoes.UserValidation;

public class UserService {

    private UserDao dao;

    public UserService() {
	this.dao = DaoFactory.createUserDao();
    }

    public boolean alterarUsername(Long id, String newUsername) {
	User user = dao.findById(id);
	if (user != null) {
	    // Verificando se o username atual não é igual ao username dele mesmo
	    if (!newUsername.equals(user.getUsername())) {
		// se nao for, verificar se o username já existe
		try {
		    UserValidation.validarUsername(newUsername, dao);
		    user.setUsername(newUsername);
		    dao.update(user);
		    return true;
		} catch (MyLoginException e) {
		    return false;
		}
	    }
	    return true; // caso o username for igual o atual não fazer nada, apenas retorna mensagem de
			 // sucesso
	}
	return false; // usuário não existe
    }

    /**
     * update name e username (verifica se o username ja existe)
     * 
     * @param id
     * @param newName
     * @param newUsername
     * @return true caso dê tudo ok, false se o usuário com id passado não exista
     * @throws MyLoginException caso login já exista
     */
    public boolean update(Long id, String newName, String newUsername, String miniatura, String foto) throws MyLoginException {
	User user = dao.findById(id);
	if (user != null) {
	    if (miniatura.equals("") && user.getMiniatura()!= null) {
		miniatura = user.getMiniatura();
	    }
	    if (foto.equals("") && user.getFoto()!= null) {
		foto = user.getFoto();
	    }
	    alterarUsername(id, newUsername);
	    user.setName(newName);
	    user.setMiniatura(miniatura);
	    user.setFoto(new Arquivo(foto, null));
	    dao.update(user);
	    return true;
	}
	return false; // usuário não existe
    }


    /**
     * 
     * @param user novo usuário a se cadastrar
     * @return true caso ocorra tudo ok
     * @throws MyLoginException caso username já exista (throws já possui mensagem)
     */
    public boolean cadastrar(User user) throws MyLoginException {
	UserValidation.validarUsername(user.getName(), dao); // caso username já exista, throw MyLoginException;
	dao.cadastrar(user);
	return true;
    }

    /**
     * método verifica a senha antes de alterar
     * @param user
     * @param senhaAtual
     * @param newPassword
     * @return true caso ocorra tudo ok
     * @throws MyLoginException caso a senha não corresponda
     */
    public boolean changePassword(User user, String senhaAtual, String newPassword) throws MyLoginException {
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

    /**
     * update sem verificação, apenas atualiza
     * @param user
     */
    public void update(User user) {
	dao.update(user);
    }
}
