package model.dao.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.entities.User;
import model.exception.MyLoginException;

public class TelaPrincipalJDBC {

    private static Scanner scan = new Scanner(System.in);
    private TelaLoginDao telaLogin;

    public TelaPrincipalJDBC() {
	this.telaLogin = DaoFactory.createTelaLoginDao();
    }

    public User logar() {
	String username = null, password = null;
	while (true) {
	    try {
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("|  [ 1 ] - Login      |");
		System.out.println("|  [ 2 ] - Cadastrar  |");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		int op = scan.nextInt();
		if (op == 1) {
		    username = pedirUser();
		    password = pedirSenha();
		    return this.telaLogin.login(username, password);
		} else if (op == 2) {
		    scan.nextLine();
		    System.out.print("Nome: ");
		    String name = scan.nextLine();
		    username = pedirUser();
		    password = pedirSenha();
		    if (this.telaLogin.cadastrar(new User(null, name, username, password))) {
			System.out.println("Cadastro realizado com sucesso! Faça login para continuar");
		    }
		} else {
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Digite uma opcao válida");
		scan.next();
	    } catch (MyLoginException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    public void telaInicial() {

    }

    private String pedirUser() {
	System.out.print("Usuario: ");
	return scan.next();
    }

    private String pedirSenha() {
	System.out.print("Senha: ");
	return scan.next();
    }

}
