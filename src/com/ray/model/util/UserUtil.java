package com.ray.model.util;

import java.text.NumberFormat;
import java.util.Scanner;

import com.ray.model.entities.User;
import com.ray.model.exception.BackButtonException;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;

public class UserUtil {

    public static String formatarNome(String nome) {
	String[] name = nome.split(" ");
	return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }

    /**
     * {@code} System.out.print(pedido + ": "); <br>
     * <br>
     * return scan.next();
     */
    public static String pedirAlgo(Scanner scan, String pedido) {
	System.out.print(pedido + ": ");
	return scan.next().trim();
    }

    public static boolean alterarNome(User user, UserService service, Scanner scan) throws BackButtonException {
	System.out.println("pressione 0 para cancelar");
	String nome = pedirAlgo(scan, "novo nome");
	ButtonUtil.botaoVoltar(nome);
	user.setName(nome);
	service.update(user);
	return true;
    }

    public static boolean alterarSenha(User user, UserService service, Scanner scan) throws BackButtonException {
	String password = pedirAlgo(scan, "Para verificar que é você, digite sua senha atual");
	try {
	    if (service.verificarSenha(user, password)) {
		System.out.println("pressione 0 para cancelar");
		password = pedirAlgo(scan, "Nova Senha");
		ButtonUtil.botaoVoltar(password);
		user.setPassword(password);
		service.update(user);
		return true;
	    }
	} catch (MyLoginException e) {
	    System.out.println(e.getMessage());
	}
	return false;
    }
    
    public static boolean alterarUsername(User user, UserService service, Scanner scan) throws BackButtonException {
	System.out.println("pressione 0 para cancelar");
	String nome = null;
	nome = pedirAlgo(scan, "novo username");
	ButtonUtil.botaoVoltar(nome);
	try{
	    service.alterarUsername(user.getId(), nome);
	    user.setUsername(nome);
	    return true;
	}catch (MyLoginException e) {
	   System.out.println(e.getMessage());
	   return false;
	}
    }
    
    public static void mostrarInfos(User user) {
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	CalculoTotalUtil ct = new CalculoTotalUtil(user);
	System.out.println("Olá, " + UserUtil.formatarNome(user.getName()) + "!");
	System.out.println("Você possui " + ct.numeroTotalCategorias() + " listas no total");
	System.out.println("Você já comprou " + ct.numTotalProdutosComprados() + " produtos de um total de " + ct.numTotalProdutos());
	System.out.println("Você já gastou "+ currencyFormatter.format(ct.totalValorReal()));
	System.out.println("Você pretendia gastar " + currencyFormatter.format( ct.totalEstipulado()));
    }

}
