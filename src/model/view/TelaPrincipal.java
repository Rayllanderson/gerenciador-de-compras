package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.entities.Categoria;
import model.entities.User;
import model.exception.CategoriaException;
import model.exception.MyLoginException;
import model.service.CategoriaService;

public class TelaPrincipal {

    private static Scanner scan = new Scanner(System.in);
    private TelaLoginDao telaLogin;

    public TelaPrincipal() {
	this.telaLogin = DaoFactory.createTelaLoginDao();
    }

    public User logar() {
	String username = null, password = null;
	while (true) {
	    try {
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("|  [ 1 ] - Login      |");
		System.out.println("|  [ 2 ] - Cadastrar  |");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		int op = scan.nextInt();
		if (op == 1) {
		    username = pedirUser();
		    password = pedirSenha();
		    return this.telaLogin.login(username, password);
		} else if (op == 2) {
		    scan.nextLine();
		    System.out.print("Nome: ");
		    String name = scan.nextLine();
		    username = pedirUser();
		    password = pedirSenha();
		    if (this.telaLogin.cadastrar(new User(null, name, username, password))) {
			System.out.println("Cadastro realizado com sucesso! Faça login para continuar");
		    }
		} else {
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Digite uma opcao válida");
		scan.next();
	    } catch (MyLoginException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    public Categoria telaCategoria(User user) {
	CategoriaService service = new CategoriaService(user);
	do {
	    System.out.println("O que deseja fazer, " + this.formatarNome(user.getName()) + "?");
	    menuCategorias();
	    try {
		int opcao = scan.nextInt();
		switch (opcao) {
		case 0:
		    System.exit(0);
		case 1:
		    service.ListarCategorias();
		    int selecionarCategoria = scan.nextInt();
		    return service.getCategoriaByNumber(selecionarCategoria);
		case 2:
		    scan.nextLine();
		    adicionarCategoria(service, user);
		    break;
		case 3:
		    editarCategoria(service);
		    break;
		case 4:
		    deletarCategoria(service);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inválida! Tente novamente.");
		scan.next();
	    } catch (CategoriaException e) {
		System.out.println(e.getMessage());
		scan.next();
	    }
	} while (true);
    }

    private void menuCategorias() {
	System.out.println("[ 1 ] - Acessar suas Listas");
	System.out.println("[ 2 ] - Adicionar nova Lista");
	System.out.println("[ 3 ] - Editar Lista");
	System.out.println("[ 4 ] - Excluir Lista");
	System.out.println("[ 0 ] - Sair");
    }

    private String formatarNome(String nome) {
	String[] name = nome.split(" ");
	return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }

    private String pedirUser() {
	System.out.print("Usuario: ");
	return scan.next();
    }

    private String pedirSenha() {
	System.out.print("Senha: ");
	return scan.next();
    }

    private void botaoVoltar() {
	
    }

    private void adicionarCategoria(CategoriaService service, User user) {
	Categoria novaCategoria = new Categoria();
	System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
	System.out.print("Nome: ");
	String nome = scan.nextLine();
	novaCategoria.setName(nome);
	novaCategoria.setUser(user);
	if (service.criarCategoria(novaCategoria))
	    System.out.println("Nova lista criada! Selecione a lista no menu principal");
    }

    private void editarCategoria(CategoriaService service) {
	String name;
	System.out.println("Selecione a lista que deseja Editar: ");
	service.ListarCategorias();
	int num = scan.nextInt();
	Categoria cat = service.getCategoriaByNumber(num);
	System.out.print("Novo nome: ");
	scan.nextLine();
	name = scan.nextLine();
	cat.setName(name);
	if (service.atualizarCategoria(cat))
	    System.out.println("Lista renomeada com sucesso!");
    }

    private void deletarCategoria(CategoriaService service) {
	System.out.println("Selecione a lista que deseja Excluir: ");
	service.ListarCategorias();
	int numCategoria = scan.nextInt();
	Categoria cat1 = service.getCategoriaByNumber(numCategoria);
	if (service.deletarCategoria(cat1)) System.out.println("Lista deletada com sucesso!");;
    }

}
