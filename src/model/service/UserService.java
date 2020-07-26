package model.service;

import model.dao.TelaLoginDao;
import model.entities.User;

public class UserService {

    private TelaLoginDao dao;
    
    public void alterarNome(User user) {
	dao.alterarNome(user);
    }
    
    public void alterarUserName(User user) {
	dao.alterarUsername(user);
    }
    
    public void alterarSenha(User user) {
	dao.alterarSenha(user);
    }
    

}
