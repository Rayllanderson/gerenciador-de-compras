package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exception.MyLoginException;

public class UserService {

    private UserDao dao;

    public UserService() {
        this.dao = DaoFactory.createTelaLoginDao();
    }

    public void updateSenhaOuNome(User user) {
        dao.update(user);
    }

    public void alterarUsername(User user) {
        dao.alterarUsername(user);
    }

    public boolean verificarSenha(User user, String password) throws MyLoginException {
        if (dao.getSenhaUser(user).equals(password)) {
            return true;
        } else {
            throw new MyLoginException("Senha inv�lida.");
        }
    }


}
