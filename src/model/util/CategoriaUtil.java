package model.util;

import java.util.Scanner;

import model.entities.Categoria;
import model.entities.User;
import model.service.CategoriaService;

public class CategoriaUtil {
    private static Scanner scan = new Scanner(System.in);
    
    public static void adicionarCategoria(CategoriaService service, User user) {
	scan.useDelimiter(System.lineSeparator());
   	Categoria novaCategoria = new Categoria();
   	System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
   	System.out.println("Pressione 0 para voltar");
   	System.out.print("Nome: ");
   	String nome = scan.next();
   	ButtonUtil.botaoVoltar(nome);
   	novaCategoria.setName(nome);
   	novaCategoria.setUser(user);
   	if (service.criarCategoria(novaCategoria))
   	    System.out.println("Nova lista criada! Selecione a lista no menu principal");
       }

    public static void editarCategoria(CategoriaService service) {
	scan.useDelimiter(System.lineSeparator());
	String name;
   	System.out.println("Selecione a lista que deseja Editar: ");
   	System.out.println("Pressione 0 para voltar");
   	service.ListarCategorias();
   	String num = scan.next();
   	ButtonUtil.botaoVoltar(num);
   	Categoria cat = service.getCategoriaByNumber(Integer.parseInt(num));
   	System.out.print("Novo nome: ");
   	name = scan.next();
   	ButtonUtil.botaoVoltar(name);
   	cat.setName(name);
   	if (service.atualizarCategoria(cat))
   	    System.out.println("Lista renomeada com sucesso!");
       }

    public static void deletarCategoria(CategoriaService service) {
   	System.out.println("Selecione a lista que deseja Excluir: ");
   	System.out.println("Pressione 0 para voltar");
   	service.ListarCategorias();
   	String num = scan.next();
   	ButtonUtil.botaoVoltar(num);
   	Categoria cat1 = service.getCategoriaByNumber(Integer.parseInt(num));
   	if (service.deletarCategoria(cat1))
   	    System.out.println("Lista deletada com sucesso!");
       }

}
