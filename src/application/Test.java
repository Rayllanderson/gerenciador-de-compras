package application;

import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.entities.User;


public class Test {
    
    private static Scanner scan = new Scanner (System.in);
    
    public static void main(String[] args) {
	
	Connection conn = DB.getConnection();

	TelaLoginDao telaLogin = DaoFactory.createTelaLogin(conn);
	
	System.out.print("Usuario: ");
	String username = scan.next();
	System.out.print("Senha: ");
	String password = scan.next();
	
	while (!telaLogin.login(username, password)) {
	    System.out.println("[ 1 ] - Tentar novamente");
	    System.out.println("[ 2 ] - Cadastrar");
	    int op = scan.nextInt(); 
	    if (op == 1) {
		System.out.print("Usuario: ");
		username = scan.next();
		System.out.print("Senha: ");
		password = scan.next();
	    }else if( op == 2){
		scan.nextLine();
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		System.out.print("Username: ");
		String user = scan.next();
		System.out.print("Password: ");
		String senha = scan.next();
		if (telaLogin.cadastrar(new User(nome, user, senha))) System.out.println("Cadastro realizado com sucesso!");
	    }
	}
	System.out.println("Logado com sucesso!");
	
	scan.close();
    }
    
}
