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
import com.ray.model.dao.CategoriaDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;

public class CategoriaJDBC implements CategoriaDao {

    private Connection conn;
    private User user;
    
    public CategoriaJDBC(Connection conn, User user) {
	this.conn = conn;
	this.user = user;
    }
    public CategoriaJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public Categoria save(Categoria categoria) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("insert into categoria (nome, id_user, orcamento) values (?, ?, ?)",
		    Statement.RETURN_GENERATED_KEYS);
	    insetCategoria(st, categoria);
	    int row = st.executeUpdate();
	    if (row > 0) {
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
		    int id = rs.getInt(1);
		    categoria.setId((long) id);
		    DB.closeResultSet(rs);
		    return categoria;
		} else {
		    throw new DbException("Erro ao inserir.");
		}
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
	return null;
    }

    @Override
    public void deletById(Long id) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("DELETE FROM categoria where id = " + id);
	    if (!(st.executeUpdate() > 0)) {
		throw new DbException("Erro ao deletar: id não existe");
	    }
	    ;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public void update(Categoria categoria) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("update categoria set nome = ?, orcamento = ? where id = ?");
	    st.setString(1, categoria.getName());
	    st.setDouble(2, categoria.getOrcamento());
	    st.setLong(3, categoria.getId());
	    st.executeUpdate();
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }
  
    @Override
    public List<Categoria> findAll() {
	List<Categoria> list = new ArrayList<>();
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement(
		    "select * from categoria where categoria.id_user = " + this.user.getId());
	    rs = st.executeQuery();
	    while (rs.next()) {
		list.add(instaciarCategoria(rs));
	    }
	    return list;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }

    private void insetCategoria(PreparedStatement st, Categoria cat) throws SQLException {
	st.setString(1, cat.getName());
	st.setLong(2, user.getId());
	st.setDouble(3, cat.getOrcamento());
    }


    private Categoria instaciarCategoria(ResultSet rs) throws SQLException {
	Categoria cat = new Categoria();
	cat.setId(rs.getLong("categoria.id"));
	cat.setName(rs.getString("categoria.nome"));
	cat.setUser(this.user);
	cat.setOrcamento(rs.getDouble("categoria.orcamento"));
	return cat;
    }
    
    @Override
    public Categoria findById(Long id) {
	PreparedStatement st = null;
	ResultSet rs = null;
	Categoria cat = new Categoria();
	try {
	    st = conn.prepareStatement("select * from categoria where categoria.id_user = " + this.user.getId() + " and categoria.id = " + id);
	    rs = st.executeQuery();
	    if (rs.next()) {
		cat = instaciarCategoria(rs);
		return cat;
	    } else {
		throw new DbException("Não encontrado");
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
    }
    
    @Override
    public List<Categoria> findByName(String name) {
	String sql = "select * from categoria where categoria.id_user =  " + this.user.getId()  + " and categoria.nome LIKE '%" + name + "%'";
	PreparedStatement st = null;
	ResultSet rs = null;
	List<Categoria> list = new ArrayList<>();
	try {
	    st = this.conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    while (rs.next()) {
		list.add(this.instaciarCategoria(rs));
	    }
	    return list;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }
}
