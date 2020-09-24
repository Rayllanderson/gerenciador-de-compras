package com.ray.application;

import com.ray.db.DbException;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.ProductoException;
import com.ray.model.view.TelaPrincipal;

public class Application {

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
	    e.printStackTrace();
	}
    }
}
