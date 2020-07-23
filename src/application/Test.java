package application;

import java.util.List;
import java.util.Scanner;

import model.dao.CategoriaDao;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Categoria;
import model.entities.Product;
import model.entities.User;
import model.service.CategoriaService;
import model.service.ProductService;
import model.view.TelaPrincipal;

public class Test {

    public static void main(String[] args) {
	TelaPrincipal telaPrincipal = new TelaPrincipal();
	User oi = null;
	CategoriaDao categoriaDao = DaoFactory.createCategoriaDao(oi);
	Categoria cat = null;
	Scanner scan = new Scanner(System.in);


	/*
	 * ProductDao product = DaoFactory.createProductDao();
	 * 
	 * Product p1 = product.findById(1); System.out.println(p1);
	 * System.out.println("_____________"); List<Product> list = product.findAll();
	 * list.forEach(System.out::println);
	 */

	oi = telaPrincipal.logar();
	System.out.println("Logado com sucesso!");
	System.out.println("Bem vindo, " + oi.getName());
	cat = telaPrincipal.telaCategoria(oi);
	ProductService pService = new ProductService(cat);
	pService.listarPordutos();

	scan.close();
    }

}
