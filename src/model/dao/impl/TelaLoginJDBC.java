package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import model.dao.TelaLoginDao;
import model.entities.User;
import model.exception.MyLoginException;

public class TelaLoginJDBC implements TelaLoginDao {

    private Connection conn;

    public TelaLoginJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public boolean login(String username, String password) {
	Statement st = null;
	ResultSet rs = null;
	String sql = "select username, senha from usuario";
	try {
	    st = conn.createStatement();
	    rs = st.executeQuery(sql);
	    while (rs.next()) {
		if (rs.getString("username").equals(username) && (rs.getString("senha").equals(password))) {
		    return true;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (MyLoginException e) {
	    System.out.println(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return false;
    }

    @Override
    public boolean cadastrar(User user) {
	PreparedStatement st = null;
	try {
	    if (!(usernameExistente(user.getUsername()))) {
		st = this.conn.prepareStatement("insert into usuario (nome, username, senha) values (?, ?, ?)");
		cadastrarUser(st, user);
		st.executeUpdate();
		return true;
	    }
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	} catch (MyLoginException e) {
	    System.out.println(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
	return false;
    }

    private void cadastrarUser(PreparedStatement st, User user) throws SQLException {
	st.setString(1, user.getName());
	st.setString(2, user.getUsername());
	st.setString(3, user.getPassword());
    }

    private boolean usernameExistente(String username) {
	Statement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select username from usuario");

	    while (rs.next()) {
		if (rs.getString("username").equals(username)) {
		    throw new MyLoginException("Username já existente!");
		}
	    }
	} catch (SQLException e) {

	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return false;
    }

}
