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
import model.dao.ProductDao;
import model.entities.Categoria;
import model.entities.Product;
import model.entities.User;

public class ProductJDBC implements ProductDao {

    private Connection conn;
    private Categoria categoria;
    private User user;

    public ProductJDBC(Connection conn, User user, Categoria categoria) {
	this.conn = conn;
	this.categoria = categoria;
	this.user = user;
    }

    @Override
    public void inserir(Product obj) {
	// TODO Auto-generated method stub

    }

    @Override
    public void atualizar(Product obj) {
	// TODO Auto-generated method stub

    }

    @Override
    public void deletById(Integer id) {
	// TODO Auto-generated method stub

    }

    @Override
    public Product findById(Integer id) {
	PreparedStatement st = null;
	ResultSet rs = null;
	Product product = null;

	try {
	    st = this.conn
		    .prepareStatement("select id, nome, preco_estipulado, preco_real from produtos where id = " + id);
	    rs = st.executeQuery();
	    if (rs.next()) {
		product = instanciarProduto(rs);
	    } else {
		throw new DbException("Ops.. produto não encontrado :(");
	    }

	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}

	return product;
    }

    @Override
    public List<Product> findAll() {
	List<Product> list = new ArrayList<>();
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.prepareStatement(
		    "select produtos.*, categoria.*,"
		    + " usuario.* from produtos inner join"
		    + " categoria on id_categoria = categoria.id"
		    + " inner join usuario on id_usuario ="
		    + " usuario.id where id_usuario = ? and id_categoria = ?");
	    st.setInt(1, this.categoria.getId());
	    st.setInt(2, this.user.getId());
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
	p.setId(rs.getInt("id"));
	p.setNome(rs.getString("nome"));
	p.setPrecoEstipulado(rs.getDouble("preco_estipulado"));
	p.setPrecoReal(rs.getDouble("preco_real"));
	p.setCategoria(this.categoria);
	p.setUser(this.user);
	return p;
    }
}
