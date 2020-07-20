package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.login.LoginException;

import db.DB;
import db.DbException;
import model.dao.TelaLoginDao;
import model.entities.User;

public class TelaLoginJDBC implements TelaLoginDao{
    
    private Connection conn;

    public TelaLoginJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public boolean login(String username, String password) {
	conn = null;
	Statement st = null;
	ResultSet rs = null;
	String sql = "select username, senha from usuario";
	try {
	   conn = DB.getConnection();
	   st = conn.createStatement();
	   rs = st.executeQuery(sql);   
	   while (rs.next()) {
	       if (rs.getString("username").equals(username)) {
		  if(!(rs.getString("senha").equals(password))) {
		       throw new LoginException("Usuário ou Senha inválidos. Insira os dados corretos e tente novamente.");
		   }
		   if (!(rs.getString("username").equals(username))) {
		       throw new LoginException("Usuário ou Senha inválidos. Insira os dados corretos e tente novamente.");
		   }
		   return true;
	       }
	   }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (LoginException e) {
	   System.out.println(e.getMessage());
	}finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return false;
    }

    @Override
    public boolean cadastrar(User user) {
	return false;
    }

    

}
