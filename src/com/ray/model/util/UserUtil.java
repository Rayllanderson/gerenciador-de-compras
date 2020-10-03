package com.ray.model.util;

import java.text.NumberFormat;

import com.ray.model.entities.User;

public class UserUtil {
    
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

}
