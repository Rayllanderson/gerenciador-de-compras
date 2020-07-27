package application;


import db.DbException;
import model.entities.Categoria;
import model.entities.User;
import model.exception.ProductoException;
import model.view.TelaPrincipal;

public class Test {

    public static void main(String[] args) {
	
	try {
	    TelaPrincipal telaPrincipal = new TelaPrincipal();
	    User user = null;
	    Categoria cat = null;
	    user = telaPrincipal.logar();
	    System.out.println("Logado com sucesso!");
	    System.out.println("Bem vindo, " + user.getName());
	    while (true) {
		try {
		    cat = telaPrincipal.telaCategoria(user);
		    telaPrincipal.telaProduto(cat);
		} catch (ProductoException e) {
		    System.out.println(e.getMessage());
		}
	    }

	} catch (DbException e) {
	    System.out.println(e.getMessage());
	}
    }
}
