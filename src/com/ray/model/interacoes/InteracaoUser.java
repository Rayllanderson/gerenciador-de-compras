package com.ray.model.interacoes;

import java.util.Scanner;

import com.ray.model.entities.User;
import com.ray.model.exception.BackButtonException;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;
import com.ray.model.util.ButtonUtil;

public class InteracaoUser {
    
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

}
