package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.TelaPrincipalDao;
import model.entities.Product;

public class Test {
    
    
    public static void main(String[] args) {
	TelaPrincipalDao telaPrincipal = DaoFactory.createTelaPrincipalDao();
	ProductDao product = DaoFactory.createProductDao();
	
	Product p1 = product.findById(5);
	System.out.println(p1);
	System.out.println("_____________");
	List<Product> list = product.findAll();
	list.forEach(System.out::println);
	
	/*telaPrincipal.logar();
	System.out.println("XAHAHA");*/
	
    }
    
}
