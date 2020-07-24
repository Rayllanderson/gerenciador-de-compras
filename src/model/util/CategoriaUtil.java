package model.util;

import java.util.InputMismatchException;
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
	    System.out.println("Deseja adicionar um orçamento para essa Lista?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - não");
	String op = scan.next();
	if (Integer.parseInt(op) == 1) {
	    inserirOrcamento(service, novaCategoria);
	}
	System.out.println("Nova lista criada! Selecione a lista no menu principal");
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

    private static void inserirOrcamento(CategoriaService service, Categoria cat) {
	System.out.print("Valor do orçamento para a lista " + cat.getName() + ": ");
	double value = scan.nextDouble();
	service.inserirOrcamento(cat, value);
    }

    public static void editarCategoria(CategoriaService service) {
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Selecione a lista que deseja Editar: ");
	System.out.println("Pressione 0 para voltar");
	service.ListarCategorias();
	String num = scan.next();
	ButtonUtil.botaoVoltar(num);
	Categoria cat = service.getCategoriaByNumber(Integer.parseInt(num));
	
	Menu.menuEditarCategorias();
	String choose = scan.next();
	switch (Integer.parseInt(choose)) {
	case 0:
	    break;
	case 1:
	    CategoriaUtil.editarCategoria(service, "tudo", cat);
	    break;
	case 2:
	    CategoriaUtil.editarCategoria(service, "nome", cat);
	    break;
	case 3:
	    CategoriaUtil.editarCategoria(service, "orcamento", cat);
	    break;
	default:
	    throw new InputMismatchException();
	}
    }
    
    private static void editarCategoria(CategoriaService service, String acao, Categoria cat) {
	if (acao.equalsIgnoreCase("nome")) {
	    String name;
	    System.out.print("Novo nome: ");
	    name = scan.next();
	    ButtonUtil.botaoVoltar(name);
	    cat.setName(name);
	}
	
	if (acao.equalsIgnoreCase("orcamento")) {
	    inserirOrcamento(service, cat);
	}
	
	if (acao.equalsIgnoreCase("tudo")) {
	    String name;
	    System.out.print("Novo nome: ");
	    name = scan.next();
	    ButtonUtil.botaoVoltar(name);
	    cat.setName(name);
	    inserirOrcamento(service, cat);
	}
	
	if (service.atualizarCategoria(cat))
	    System.out.println("Lista editada com sucesso!");
    }

}
