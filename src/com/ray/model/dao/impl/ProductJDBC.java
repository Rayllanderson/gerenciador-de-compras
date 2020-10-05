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
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;

public class ProductJDBC implements ProductDao {

    private Connection conn;
    private Categoria categoria;

    public ProductJDBC(Connection conn, Categoria categoria) {
	this.conn = conn;
	this.categoria = categoria;
    }

    @Override
    public void save(Product obj) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("\r\n"
		    + "insert into produtos (nome, preco_estipulado, preco_real, id_categoria, comprado) values (?, ?, ?, ?, ?)",
		    Statement.RETURN_GENERATED_KEYS);
	    this.inserirProduto(st, obj);
	    if (st.executeUpdate() > 0) {
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
		    obj.setId(rs.getLong(1));
		}
		DB.closeResultSet(rs);
	    } else
		throw new DbException("Ocorreu um erro ao inserir. Tente novamente.");
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public void update(Product obj) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("update produtos set nome = ?, preco_estipulado = ?, preco_real = ?, "
		    + "id_categoria = ?, comprado = ? where id = ?");
	    this.inserirProduto(st, obj);
	    st.setLong(6, obj.getId());
	    st.executeUpdate();
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}

    }

    @Override
    public void deletById(Long id) {
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement("DELETE FROM produtos " + "WHERE Id = ?");
	    st.setLong(1, id);
	    int row = st.executeUpdate();
	    if (row == 0) {
		throw new DbException("Ops, id não existe ou ocorreu um erro inesperado");
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
    }

    @Override
    public List<Product> findAll() {
	List<Product> list = new ArrayList<>();
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.prepareStatement("select * from produtos where id_categoria = " + this.categoria.getId());
	    rs = st.executeQuery();
	    while (rs.next()) {
		Product p = instanciarProduto(rs);
		list.add(p);
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return list;
    }

    private Product instanciarProduto(ResultSet rs) throws SQLException {
	Product p = new Product();
	p.setId(rs.getLong("id"));
	p.setNome(rs.getString("nome"));
	p.setPrecoEstipulado(rs.getDouble("preco_estipulado"));
	p.setPrecoReal(rs.getDouble("preco_real"));
	p.setCategoria(this.categoria);
	p.setComprado(rs.getBoolean("comprado"));
	return p;
    }

    private void inserirProduto(PreparedStatement st, Product p) throws SQLException {
	st.setString(1, p.getNome());
	st.setDouble(2, p.getPrecoEstipulado());
	st.setDouble(3, p.getPrecoReal());
	st.setLong(4, this.categoria.getId());
	st.setBoolean(5, p.isComprado());
    }

    @Override
    public Product findById(Long id) {
	String sql = "select * from produtos where produtos.id = " + id;
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    if (rs.next()) {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setNome(rs.getString("nome"));
		p.setPrecoEstipulado(rs.getDouble("preco_estipulado"));
		p.setPrecoReal(rs.getDouble("preco_real"));
		p.setCategoria(this.categoria);
		p.setComprado(rs.getBoolean("comprado"));
		return p;
	    }
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return null;
    }

    @Override
    public List<Product> findByName(String name) {
	String sql = "select produtos.* from produtos inner join categoria on id_categoria = categoria.id where produtos.nome LIKE '%"
		+ name + "%' and id_categoria = " + this.categoria.getId();
	PreparedStatement st = null;
	ResultSet rs = null;
	List<Product> list = new ArrayList<>();
	try {
	    st = this.conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    while (rs.next()) {
		list.add(instanciarProduto(rs));
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
