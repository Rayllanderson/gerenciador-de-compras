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
	System.out.println("Categoria selecionada: " + cat + " com o id: " + cat.getId());
	ProductDao productDao = DaoFactory.createProductDao(oi, cat);
	
	// teste INSERIR categoria ----------------- FUNCIONANDO
	/*
	 * cat = new Categoria(null, "feira", oi); categoriaDao.inserir(cat);
	 */

	// teste FINDBYID --------------------------- FUNCIONANDO
	/*
	 * cat = categoriaDao.findById(4); System.out.println(cat);
	 */

	// teste FINDALL --------------------------- FUNCIONANDO
	/*
	 * List <Categoria> list = categoriaDao.findAll();
	 * list.forEach(System.out::println);
	 */

	// teste ATUALIZAR -------------------------- FUNCIONANDO
	/*
	 * cat = categoriaDao.findById(4); cat.setName("compras semanais");
	 * categoriaDao.atualizar(cat);
	 */

	// teste DELETAR -------------------------- FUNCIONANDO
	// categoriaDao.deletById(5);
	
	
	/********************************************************/

	// teste INSERIR produto ----------------- FUNCIONANDO
	
	/*Product p = new Product(null, "SSD 538", 500.00, 620.75, false, oi, cat);
	productDao.inserir(p);*/
	 
	// teste FINDALL --------------------------- FUNCIONANDO
	/*		
	List<Product> list = productDao.findAll();
	list.forEach(System.out::println);
	 */

	// teste ATUALIZAR -------------------------- FUNCIONANDO
	
	/*Product p = new Product(12, "Pasta Térmica", 30.00, 22.00, true, oi, cat);
	productDao.atualizar(p);
	List<Product> list = productDao.findAll();
	list.forEach(System.out::println);*/

	// teste DELETAR -------------------------- FUNCIONANDO
	/*productDao.deletById(10);
	productDao.deletById(11);*/
	/*
	List<Product> list = productDao.findAll();
	list.forEach(System.out::println);*/

	scan.close();
    }

}
