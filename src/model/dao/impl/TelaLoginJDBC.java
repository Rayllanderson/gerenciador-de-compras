package model.dao.impl;

import java.sql.Connection;

import model.dao.TelaLoginDao;
import model.entities.User;

public class TelaLoginJDBC implements TelaLoginDao{
    
    private Connection conn;
    private User user;

    public TelaLoginJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public boolean login() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean cadastrar() {
	// TODO Auto-generated method stub
	return false;
    }
    
    
    

}
