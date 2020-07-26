package model.service;

import model.dao.TelaLoginDao;
import model.entities.User;

public class UserService {

    private TelaLoginDao dao;
    
    public void updateSenhaOuNome(User user) {
	dao.update(user);
    }
    
    public void alterarUsername(User user) {
	dao.alterarUsername(user);
    }

    

}
