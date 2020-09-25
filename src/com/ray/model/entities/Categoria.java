package com.ray.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categoria implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;
    private User user;
    private Double orcamento;
    
    private List <Product> productList = new ArrayList<>();
    
    
    public Categoria () {};
    
    
    public Categoria(Integer id, String name, User user) {
	this.id = id;
	this.name = name;
	this.user = user;
    }  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Double getOrcamento() {
	return orcamento;
    }

    public void setOrcamento(Double orcamento) {
	this.orcamento = orcamento;
    }
    
    public void adicionarProduto(Product p) {
	this.productList.add(p);
    }
    
    public void deletarProduto(Product p) {
	this.productList.remove(p);
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Categoria other = (Categoria) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
    

    @Override
    public String toString() {
	return name;
    }


}
