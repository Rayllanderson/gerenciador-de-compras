package model.dao.impl;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.dao.TelaPrincipalDao;
import model.entities.User;
import model.exception.MyLoginException;

public class TelaPrincipalJDBC implements TelaPrincipalDao {

    private static Scanner scan = new Scanner(System.in);
    private TelaLoginDao telaLogin;
    private Connection conn;
    private int userId; //pegar o id do user logado, com isso posso ter acesso a tudo!

    public TelaPrincipalJDBC(Connection connection) {
	this.telaLogin = DaoFactory.createTelaLoginDao();
	this.conn = connection;
    }

    @Override
    public void logar() {
	String username = null, password = null;
	boolean flag = false, login = false;
	do {
	    try {
		while (!login) {
		    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		    System.out.println("|  [ 1 ] - Login      |");
		    System.out.println("|  [ 2 ] - Cadastrar  |");
		    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		    int op = scan.nextInt();
		    if (op == 1) {
			username = pedirUser();
			password = pedirSenha();
			this.userId = this.telaLogin.login(username, password);
			if (userId > 0) {
			    login = true;
			};
		    } else if (op == 2) {
			scan.nextLine();
			System.out.print("Nome: ");
			String name = scan.nextLine();
			username = pedirUser();
			password = pedirSenha();
			if (this.telaLogin.cadastrar(new User(name, username, password))) {
			    System.out.println("Cadastro realizado com sucesso! Faça login para continuar");
			}
		    } else {
			throw new InputMismatchException();
		    }
		}
		flag = true;
	    } catch (InputMismatchException e) {
		System.out.println("Digite uma opcao válida");
		scan.next();
	    } catch (MyLoginException e) {
		System.out.println(e.getMessage());
	    }
	} while (!flag);

	System.out.println("Logado com sucesso!");

    }

    @Override
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
