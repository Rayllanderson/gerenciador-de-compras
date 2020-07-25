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
	try {
	    Categoria novaCategoria = new Categoria();
	    System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
	    System.out.println("Pressione 0 para voltar");
	    System.out.print("Nome: ");
	    String nome = scan.next();
	    ButtonUtil.botaoVoltar(nome);
	    novaCategoria.setName(nome);
	    novaCategoria.setUser(user);
	    if (service.criarCategoria(novaCategoria)) {
		System.out.println("Deseja adicionar um orçamento para essa Lista?");
		System.out.println("[ 1 ] - sim");
		System.out.println("[ 2 ] - não");
		String op = scan.next();
		if (Integer.parseInt(op) == 1) {
		    inserirOrcamento(service, novaCategoria);
		}
		System.out.println("Nova lista criada! Selecione a lista no menu principal");
	    }
	} catch (InputMismatchException e) {
	    System.out.println("Valor inválido!");
	}
    }

    public static void deletarCategoria(CategoriaService service) throws NumberFormatException {
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Selecione a lista que deseja Excluir: ");
	System.out.println("Pressione 0 para voltar");
	service.ListarCategorias();
	String num = scan.next();
	ButtonUtil.botaoVoltar(num);
	Categoria cat1 = service.getCategoriaByNumber(Integer.parseInt(num));
	if (ButtonUtil.confirmar("deletar a lista " + cat1.getName())) {
	    if (service.deletarCategoria(cat1))
		System.out.println("Lista deletada com sucesso!");
	} else {
	    System.out.println("Lista não deletada.");
	}

    }

    private static void inserirOrcamento(CategoriaService service, Categoria cat) throws InputMismatchException {
	System.out.print("Valor do orçamento para a lista " + cat.getName() + ": ");
	double value = scan.nextDouble();
	service.inserirOrcamento(cat, value);
    }

    public static void editarTudoCategoria(CategoriaService service, String oqVaiSerEditado, Categoria cat) {
	if (oqVaiSerEditado.equalsIgnoreCase("nome")) {
	    String name;
	    System.out.print("Novo nome: ");
	    name = scan.next();
	    ButtonUtil.botaoVoltar(name);
	    if (ButtonUtil.confirmar("renomear")) {
		cat.setName(name);
	    } else {
		System.out.println("Lista não renomeada");
	    }

	}
	if (oqVaiSerEditado.equalsIgnoreCase("orcamento")) {
	    try {
		inserirOrcamento(service, cat);
	    } catch (InputMismatchException e) {
		System.out.println("Digite um valor válido");
	    }
	}
	if (oqVaiSerEditado.equalsIgnoreCase("tudo")) {
	    String name;
	    System.out.print("Novo nome: ");
	    name = scan.next();
	    ButtonUtil.botaoVoltar(name);
	    if (ButtonUtil.confirmar("alterar os valores")) {
		cat.setName(name);
		inserirOrcamento(service, cat);
	    } else {
		System.out.println("Lista não atualizada.");
	    }
	}
	if (service.atualizarCategoria(cat))
	    System.out.println("Lista editada com sucesso!");
	else
	    System.out.println("Ocorreu um erro ao editar. Tente novamente");
    }

    public static void adicionarOrcamento(CategoriaService service, Categoria cat) {
	try {
	    inserirOrcamento(service, cat);
	} catch (InputMismatchException e) {
	    System.out.println("Digite um valor válido");
	}
    }

}
