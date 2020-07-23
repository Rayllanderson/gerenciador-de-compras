package model.util;

import java.util.Scanner;

import model.entities.Categoria;
import model.entities.User;
import model.service.CategoriaService;

public class CategoriaUtil {
    private static Scanner scan = new Scanner(System.in);
    
    public static void adicionarCategoria(CategoriaService service, User user) {
   	Categoria novaCategoria = new Categoria();
   	System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
   	System.out.println("Pressione 0 para voltar");
   	System.out.print("Nome: ");
   	String nome = scan.nextLine();
   	ButtonUtil.botaoVoltar(nome);
   	novaCategoria.setName(nome);
   	novaCategoria.setUser(user);
   	if (service.criarCategoria(novaCategoria))
   	    System.out.println("Nova lista criada! Selecione a lista no menu principal");
       }

    public static void editarCategoria(CategoriaService service) {
   	String name;
   	System.out.println("Selecione a lista que deseja Editar: ");
   	System.out.println("Pressione 0 para voltar");
   	service.ListarCategorias();
   	int num = scan.nextInt();
   	ButtonUtil.botaoVoltar(num);
   	Categoria cat = service.getCategoriaByNumber(num);
   	System.out.print("Novo nome: ");
   	scan.nextLine();
   	name = scan.nextLine();
   	ButtonUtil.botaoVoltar(name);
   	cat.setName(name);
   	if (service.atualizarCategoria(cat))
   	    System.out.println("Lista renomeada com sucesso!");
       }

    public static void deletarCategoria(CategoriaService service) {
   	System.out.println("Selecione a lista que deseja Excluir: ");
   	System.out.println("Pressione 0 para voltar");
   	service.ListarCategorias();
   	int numCategoria = scan.nextInt();
   	ButtonUtil.botaoVoltar(numCategoria);
   	Categoria cat1 = service.getCategoriaByNumber(numCategoria);
   	if (service.deletarCategoria(cat1))
   	    System.out.println("Lista deletada com sucesso!");
       }

}
