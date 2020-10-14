package com.ray.model.util;

import com.ray.model.entities.User;

public class UserUtil {
   
    private CalculoTotal ct;
    
    public UserUtil(User user) {
	this.ct = new CalculoTotal(user);
    }
    
    public static String formatarNome(String nome) {
	String[] name = nome.split(" ");
	return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }
    
    public int getNumeroTotalCategorias() {
	return ct.numeroTotalCategorias();
    }
    
    public int getNumTotalProdutosComprados() {
	return ct.numTotalProdutosComprados();
    }
    
    public int getNumTotalProdutos() {
	return ct.numTotalProdutos();
    }
    
    public Double getTotalReal() {
	return ct.totalValorReal();
    }

    public Double getTotalEstipulado() {
	return ct.totalEstipulado();
    }

}
