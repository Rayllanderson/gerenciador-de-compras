package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CategoriaDao;
import model.entities.Categoria;
import model.entities.User;

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
    public void inserir(Categoria categoria) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("insert into categoria (nome, id_user) values (?, ?)",
		    Statement.RETURN_GENERATED_KEYS);
	    insetCategoria(st, categoria);
	    int row = st.executeUpdate();
	    if (row > 0) {
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
		    int id = rs.getInt(1);
		    categoria.setId(id);
		    DB.closeResultSet(rs);
		} else {
		    throw new DbException("Ops, parece que algo deu errado. :(");
		}
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public void deletById(Integer id) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("DELETE FROM CATEGORIA where id = " + id);
	    if (!(st.executeUpdate() > 0)) {
		throw new DbException("Ops, id não existe");
	    }
	    ;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public void atualizar(Categoria categoria) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("update categoria set nome = ? where id = ?");
	    st.setString(1, categoria.getName());
	    st.setInt(2, categoria.getId());
	    st.executeUpdate();
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public Categoria findById(Integer id) {
	PreparedStatement st = null;
	ResultSet rs = null;
	Categoria cat = new Categoria();
	try {
	    st = conn.prepareStatement("select categoria.*, usuario.*"
		    + " from categoria inner join usuario on categoria.id_user = usuario.id where categoria.id = "
		    + id);
	    rs = st.executeQuery();
	    if (rs.next()) {
		User user = instaciarUsuario(rs);
		cat = instaciarCategoria(rs, user);
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
    public List<Categoria> findAll() {
	List<Categoria> list = new ArrayList<>();
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement(
		    "select * from categoria where categoria.id_user = " + this.user.getId());
	    rs = st.executeQuery();
	    while (rs.next()) {
		Categoria cat = instaciarCategoria(rs, user);
		list.add(cat);
	    }
	    return list;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }


    @Override
    public Categoria findByName(String name) {
	// TODO Auto-generated method stub
	return null;
    }

    private void insetCategoria(PreparedStatement st, Categoria cat) throws SQLException {
	st.setString(1, cat.getName());
	st.setInt(2, cat.getUser().getId());
    }

    private User instaciarUsuario(ResultSet rs) throws SQLException {
	User user = new User();
	user.setId(rs.getInt("usuario.id"));
	user.setName(rs.getString("usuario.nome"));
	user.setUsername(rs.getString("usuario.username"));
	user.setPassword(rs.getString("usuario.senha"));
	return user;
    }

    private Categoria instaciarCategoria(ResultSet rs, User user) throws SQLException {
	Categoria cat = new Categoria();
	cat.setId(rs.getInt("categoria.id"));
	cat.setName(rs.getString("categoria.nome"));
	cat.setUser(user);
	return cat;
    }

}
