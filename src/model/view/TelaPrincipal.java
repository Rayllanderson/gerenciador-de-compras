package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.entities.Categoria;
import model.entities.Product;
import model.entities.User;
import model.exception.BackButtonException;
import model.exception.CategoriaException;
import model.exception.MyLoginException;
import model.exception.ProductoException;
import model.service.CategoriaService;
import model.service.ProductService;

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
	    } catch (BackButtonException e) {
	    }
	} while (true);
    }

    public boolean telaProduto(Categoria cat) {
	ProductService service = new ProductService(cat);
	while (true) {
	    service.listarPordutos();
	    this.menuProdutos();
	    try {
		int opcao = scan.nextInt();
		switch (opcao) {
		case 0:
		    System.exit(0);
		case 1:
		    this.adicionarProduto(service, cat);
		case 2:
		    while (this.menuEditarProduto(service));
		    break;
		case 3:
		    Product p = selecionarProduto(service, "Excluir");
		    service.deletar(p);
		    break;
		case 4:
		    Product p1 = selecionarProduto(service, "Marcar como comprado");
		    this.marcarComoConcluido(service, p1);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inválida! Tente novamente.");
		scan.next();
	    } catch (ProductoException e) {
		System.out.println(e.getMessage());
	    }
	}

    }

    private void menuCategorias() {
	System.out.println("[ 1 ] - Acessar suas Listas");
	System.out.println("[ 2 ] - Adicionar nova Lista");
	System.out.println("[ 3 ] - Editar Lista");
	System.out.println("[ 4 ] - Excluir Lista");
	System.out.println("[ 0 ] - Sair");
    }

    private void menuProdutos() {
	System.out.println("[ 1 ] - Adicionar Novo Produto");
	System.out.println("[ 2 ] - Editar Produto");
	System.out.println("[ 3 ] - Excluir Produto");
	System.out.println("[ 4 ] - Marcar como comprado");
	System.out.println("[ 0 ] - Sair");
    }

    private void menuEditarProduto() {
	System.out.println("[ 1 ] - Editar tudo");
	System.out.println("[ 2 ] - Editar nome");
	System.out.println("[ 3 ] - Editar Preço Estipulado");
	System.out.println("[ 4 ] - Editar Preço Real");
	System.out.println("[ 5 ] - Marcar como comprado");
	System.out.println("[ 0 ] - Voltar");
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

    private void botaoVoltar(Object opcao) {
	if (opcao.equals("0") || opcao.equals(0)) {
	    throw new BackButtonException();
	}
    }

    private void adicionarCategoria(CategoriaService service, User user) {
	Categoria novaCategoria = new Categoria();
	System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
	System.out.println("Pressione 0 para voltar");
	System.out.print("Nome: ");
	String nome = scan.nextLine();
	botaoVoltar(nome);
	novaCategoria.setName(nome);
	novaCategoria.setUser(user);
	if (service.criarCategoria(novaCategoria))
	    System.out.println("Nova lista criada! Selecione a lista no menu principal");
    }

    private void editarCategoria(CategoriaService service) {
	String name;
	System.out.println("Selecione a lista que deseja Editar: ");
	System.out.println("Pressione 0 para voltar");
	service.ListarCategorias();
	int num = scan.nextInt();
	this.botaoVoltar(num);
	Categoria cat = service.getCategoriaByNumber(num);
	System.out.print("Novo nome: ");
	scan.nextLine();
	name = scan.nextLine();
	this.botaoVoltar(name);
	cat.setName(name);
	if (service.atualizarCategoria(cat))
	    System.out.println("Lista renomeada com sucesso!");
    }

    private void deletarCategoria(CategoriaService service) {
	System.out.println("Selecione a lista que deseja Excluir: ");
	System.out.println("Pressione 0 para voltar");
	service.ListarCategorias();
	int numCategoria = scan.nextInt();
	botaoVoltar(numCategoria);
	Categoria cat1 = service.getCategoriaByNumber(numCategoria);
	if (service.deletarCategoria(cat1))
	    System.out.println("Lista deletada com sucesso!");
	;
    }

    private void adicionarProduto(ProductService service, Categoria cat) {
	System.out.print("Nome: ");
	String nome = scan.nextLine();
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	double valorEstipulado = scan.nextDouble();
	System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	System.out.print("Qual o preço que você realmente pagou? R$");
	double valorReal = scan.nextDouble();
	Product p = new Product(null, nome, valorEstipulado, valorReal, false, cat.getUser(), cat);
	if (!(valorReal == 0)) {
	    service.marcarComoConcluido(p, valorReal);
	}
	service.inserir(p);
    }

    private boolean menuEditarProduto(ProductService service) {
	while (true) {
	    try {
		Product p = selecionarProduto(service, "Editar");
		menuEditarProduto();
		int opcaoEditarProduto = scan.nextInt();
		switch (opcaoEditarProduto) {
		case 0:
		    return true;
		case 1:
		    editarProduto(service, p);
		    break;
		case 2:
		    System.out.print("Novo nome: ");
		    String name = scan.nextLine();
		    service.editarNome(p, name);
		    break;
		case 3:
		    System.out.print("Novo valor estipulado: R$");
		    double valorEstipulado = scan.nextDouble();
		    service.editarPrecoEstipulado(p, valorEstipulado);
		    break;
		case 4:
		    editarValorReal(service, p);
		    break;
		case 5:
		    marcarComoConcluido(service, p);
		    break;
		default:
		    throw new InputMismatchException("Opção inválida");
		}
	    } catch (InputMismatchException e) {
		System.out.println(e.getMessage());
		scan.next();
	    }
	}
    }

    private void editarProduto(ProductService service, Product p) {
	System.out.print("Nome: ");
	String nome = scan.nextLine();
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	double valorEstipulado = scan.nextDouble();
	System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	System.out.print("Qual o preço que você realmente pagou? R$");
	double valorReal = scan.nextDouble();
	if (!(valorReal == 0)) {
	    service.marcarComoConcluido(p, valorReal);
	}
	p.setNome(nome);
	p.setPrecoEstipulado(valorEstipulado);
	service.atualizar(p);
    }

    private void marcarComoConcluido(ProductService service, Product p) {
	if (p.getPrecoReal() != 0) {
	    System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	    System.out.println("[ 1 ] - sim");
	    System.out.println("[ 2 ] - nop");
	    if (scan.nextInt() == 1) {
		editarValorReal(service, p);
	    }
	}
	double value = p.getPrecoReal();
	service.marcarComoConcluido(p, value);
    }

    private void editarValorReal(ProductService service, Product p) {
	System.out.print("Valor real: R$");
	double valorReal = scan.nextDouble();
	service.editarPrecoReal(p, valorReal);
    }
    
    private Product selecionarProduto(ProductService service, String acao) {
	service.listarPordutos();
	System.out.print("Esolha qual produto deseja " + acao + ": ");
	int produtoEscolhido = scan.nextInt();
	Product p = service.getProdutoByNumer(produtoEscolhido);
	return p;
    }

}
