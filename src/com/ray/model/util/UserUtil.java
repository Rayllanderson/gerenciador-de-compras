package com.ray.model.util;

import java.text.NumberFormat;

import com.ray.model.entities.User;

public class UserUtil {
    
    private CalculoTotal ct;
    private NumberFormat currencyFormatter;
    
    public UserUtil(User user) {
	this.ct = new CalculoTotal(user);
	this.currencyFormatter = NumberFormat.getCurrencyInstance();
    }
    
    public static String formatarNome(String nome) {
	String[] name = nome.split(" ");
	return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }

    public static void mostrarInfos(User user) {
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	CalculoTotal ct = new CalculoTotal(user);
	System.out.println("Olá, " + UserUtil.formatarNome(user.getName()) + "!");
	System.out.println("Você possui " + ct.numeroTotalCategorias() + " listas no total");
	System.out.println("Você já comprou " + ct.numTotalProdutosComprados() + " produtos de um total de " + ct.numTotalProdutos());
	System.out.println("Você já gastou "+ currencyFormatter.format(ct.totalValorReal()));
	System.out.println("Você pretendia gastar " + currencyFormatter.format( ct.totalEstipulado()));
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
    
    public String getTotalValorReal() {
	return currencyFormatter.format(ct.totalValorReal());
    }

    public String getTotalEstipulado() {
	return currencyFormatter.format(ct.totalEstipulado());
    }
}
