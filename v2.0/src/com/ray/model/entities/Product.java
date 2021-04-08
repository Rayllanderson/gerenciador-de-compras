package com.ray.model.entities;

import java.io.Serializable;

import com.ray.model.util.HtmlColors;

public class Product implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double precoEstipulado; //quanto que acho que vai ser tal produto
    private Double precoReal; //quanto que ele realmente foi
    private Categoria categoria;
    private boolean comprado;
    
    public Product(Long id, String nome, Double precoEstipulado, Double precoReal, boolean comprado, Categoria categoria) {
	this.id = id;
	this.nome = nome;
	this.precoEstipulado = precoEstipulado;
	this.precoReal = precoReal;
	this.comprado = comprado;
	this.categoria = categoria;
    }

    public Product() {
	// TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoEstipulado() {
        return precoEstipulado;
    }

    public void setPrecoEstipulado(Double precoEstipulado) {
        this.precoEstipulado = precoEstipulado;
    }

    public Double getPrecoReal() {
        return precoReal;
    }

    public void setPrecoReal(Double precoReal) {
        this.precoReal = precoReal;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isComprado() {
	return comprado;
    }

    public void setComprado(boolean comprado) {
	this.comprado = comprado;
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
	Product other = (Product) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
	return nome;
    }
    
    public String comprado() {
	return comprado ? HtmlColors.GREEN +"Sim" + HtmlColors.BLACK : HtmlColors.RED +"Não" + HtmlColors.BLACK;
    }
    
    public String getValorRealEmReal() {
	return Double.toString(this.precoReal).replace('.', ',');
    }
    
    public String getValorEstipuladoEmReal() {
	return Double.toString(this.precoEstipulado).replace('.', ',');
    }
}
