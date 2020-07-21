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
import model.entities.Product;

public class ProductJDBC implements ProductDao{
        
    private Connection conn;
    
    public ProductJDBC(Connection conn) {
	this.conn = conn;
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
	    st = this.conn.prepareStatement("select id, nome, preco_estipulado, preco_real from produtos where id = " + id);
	    rs = st.executeQuery();
	    if (rs.next()) {
		product = instanciarProduto(rs);
	    }else {
		throw new DbException("Ops.. produto não encontrado :(");
	    }
	    
	}catch (SQLException e) {
	    throw new DbException(e.getMessage());
	}finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	
	return product;
    }

    @Override
    public List<Product> findAll() {
	List <Product> list = new ArrayList<>();
	Statement st = null;
	ResultSet rs = null;
	try {  
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select id, nome, preco_estipulado, preco_real from produtos");
	    while (rs.next()) {
		list.add(new Product(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_estipulado"), rs.getDouble("preco_real")));
	    }  
	}catch (SQLException e) {
	    throw new DbException(e.getMessage());
	}finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return list;
    }
    
    private Product instanciarProduto (ResultSet rs) throws SQLException {
	Product p = new Product();
	p.setId(rs.getInt("id"));
	p.setNome(rs.getString("nome"));
	p.setPrecoEstipulado(rs.getDouble("preco_estipulado"));
	p.setPrecoReal(rs.getDouble("preco_real"));
	return p;
    }
}
