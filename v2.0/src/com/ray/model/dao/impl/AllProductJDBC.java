package com.ray.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ray.db.DB;
import com.ray.db.DbException;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.entities.User;

public class AllProductJDBC extends ProductJDBC {

    private User user;

    public AllProductJDBC(Connection conn, User user) {
	super(conn);
	this.user = user;
    }  
    
    /**
     * 
     * @return lista contendo todos os produtos do usuário, independente de categoria
     */
    @Override
    public List<Product> findAll() {
	List<Product> list = new ArrayList<>();
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = super.conn.prepareStatement(
		    "select produtos.*, categoria.nome as nome_categoria from produtos inner join categoria on "
			    + "id_categoria = categoria.id where id_user = " + this.user.getId());
	    rs = st.executeQuery();
	    while (rs.next()) {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setNome(rs.getString("nome"));
		p.setPrecoEstipulado(rs.getDouble("preco_estipulado"));
		p.setPrecoReal(rs.getDouble("preco_real"));
		Categoria cat = new Categoria(rs.getLong("id_categoria"), rs.getString("nome_categoria"), user);
		p.setCategoria(cat);
		p.setComprado(rs.getBoolean("comprado"));
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
    
    
    @Override
    public List<Product> findByName(String name) {
	String sql = "select produtos.*, categoria.id as cat_id from produtos inner join categoria on id_categoria = categoria.id where produtos.nome LIKE '%"
		+ name + "%' and id_user = " + this.user.getId();
	PreparedStatement st = null;
	ResultSet rs = null;
	List<Product> list = new ArrayList<>();
	try {
	    st = this.conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    while (rs.next()) {
		list.add(this.instanciarProduto(rs));
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
    protected Product instanciarProduto(ResultSet rs) throws SQLException {
	Product p = super.instanciarProduto(rs);
	p.setCategoria(new Categoria());
	p.getCategoria().setId(rs.getLong("cat_id"));
        return p;
    }

}
