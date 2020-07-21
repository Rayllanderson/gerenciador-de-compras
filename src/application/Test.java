package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.impl.TelaPrincipalJDBC;
import model.entities.Product;
import model.entities.User;

public class Test {
    
    
    public static void main(String[] args) {
	TelaPrincipalJDBC telaPrincipal = new TelaPrincipalJDBC();
	/*ProductDao product = DaoFactory.createProductDao();
	
	Product p1 = product.findById(1);
	System.out.println(p1);
	System.out.println("_____________");
	List<Product> list = product.findAll();
	list.forEach(System.out::println);*/
	
	User oi = telaPrincipal.logar();
	System.out.println("Logado com sucesso!");
	System.out.println("Bem vindo, " + oi.getName());
	
    }
    
}
