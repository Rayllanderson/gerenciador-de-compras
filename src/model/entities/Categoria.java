package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private Integer id;
    private String name;
    private List <Product> produtos = new ArrayList<>();
    private User user;
    
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

    public List<Product> getProdutos() {
        return produtos;
    }
    
    public void adicionarProduto(Product produto) {
	produtos.add(produto);
    }
    
    public void removerProduto(Product produto) {
	produtos.remove(produto);
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
