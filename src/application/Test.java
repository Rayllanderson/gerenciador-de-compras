package application;

import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.TelaLoginDao;


public class Test {

    public static void main(String[] args) {
	
	Scanner scan = new Scanner (System.in);

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
	    }
	}
	System.out.println("Logado com sucesso");
	
	scan.close();
    }
}
