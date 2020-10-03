package com.ray.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ray.db.DB;
import com.ray.db.DbException;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;

public class UserDaoJDBC implements UserDao {

    private Connection conn;

    public UserDaoJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public User login(String username, String password) throws MyLoginException {
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement("select * from usuario where username = ? and senha = ?");
	    st.setString(1, username);
	    st.setString(2, password);
	    rs = st.executeQuery();
	    if (rs.next()) {
		return new User(rs.getInt("id"), rs.getString("nome"), rs.getString("username"), rs.getString("senha"));
	    } else {
		throw new MyLoginException("Usu�rio ou Senha inv�lidos.");
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }

    @Override
    public boolean cadastrar(User user) {
	PreparedStatement st = null;
	try {
	    st = this.conn.prepareStatement("insert into usuario (nome, username, senha) values (?, ?, ?)");
	    setUser(st, user);
	    st.executeUpdate();
	    return true;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} catch (MyLoginException e) {
	    System.out.println(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
	return false;
    }

    private void setUser(PreparedStatement st, User user) throws SQLException {
	st.setString(1, user.getName());
	st.setString(2, user.getUsername());
	st.setString(3, user.getPassword());
    }

    @Override
    public User findById(Integer id) throws MyLoginException {
	Statement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select * from usuario where id = " + id);
	    if (rs.next()) {
		return new User(rs.getInt("id"), rs.getString("nome"), rs.getString("username"), rs.getString("senha"));
	    } else {
		return null;
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }

    @Override
    public List<String> findAllUsernames() {
	Statement st = null;
	ResultSet rs = null;
	List<String> list = new ArrayList<>();
	try {
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select username from usuario");
	    while (rs.next()) {
		list.add(rs.getString("username"));
	    }
	    return list;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public void update(User user) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("update usuario set nome = ?, username = ?, senha = ? where id = ?");
	    st.setString(1, user.getName());
	    st.setString(2, user.getUsername());
	    st.setString(3, user.getPassword());
	    st.setInt(4, user.getId());
	    st.executeUpdate();
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
    }

}
