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
import model.exception.ListaVaziaException;
import model.exception.MyLoginException;
import model.exception.ProductoException;
import model.service.CategoriaService;
import model.service.ProductService;
import model.util.ButtonUtil;
import model.util.CategoriaUtil;
import model.util.Menu;
import model.util.ProdutosUtil;

public class TelaPrincipal {

    private static Scanner scan = new Scanner(System.in);
    private TelaLoginDao telaLogin;

    public TelaPrincipal() {
	this.telaLogin = DaoFactory.createTelaLoginDao();
	scan.useDelimiter(System.lineSeparator());
    }

    // --------------------------- MENU LOGIN ----------------------------------
    public User logar() {
	String username = null, password = null;
	while (true) {
	    try {
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("|  [ 1 ] - Login      |");
		System.out.println("|  [ 2 ] - Cadastrar  |");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
		String op = scan.next();
		if (Integer.parseInt(op) == 1) {
		    username = pedirUser();
		    password = pedirSenha();
		    return this.telaLogin.login(username, password);
		} else if (Integer.parseInt(op) == 2) {
		    System.out.print("Nome: ");
		    String name = scan.next();
		    username = pedirUser();
		    password = pedirSenha();
		    if (this.telaLogin.cadastrar(new User(null, name, username, password))) {
			System.out.println("Cadastro realizado com sucesso! Faça login para continuar");
		    }
		} else {
		    throw new MyLoginException("Digite uma opção válida");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Digite uma opcao válida");
	    } catch (MyLoginException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    // --------------------------- MENUS CATEGORIA
    // ----------------------------------
    public Categoria telaCategoria(User user) {
	CategoriaService service = new CategoriaService(user);
	do {
	    String opcao = null;
	    System.out.println("O que deseja fazer, " + this.formatarNome(user.getName()) + "?");
	    Menu.menuCategorias();
	    try {
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    System.exit(0);
		case 1:
		    System.out.println("Pressione 0 para cancelar");
		    service.ListarCategorias();
		    int selecionarCategoria = scan.nextInt();
		    ButtonUtil.botaoVoltar(selecionarCategoria);
		    return service.getCategoriaByNumber(selecionarCategoria);
		case 2:
		    CategoriaUtil.adicionarCategoria(service, user);
		    break;
		case 3:
		   CategoriaUtil.editarCategoria(service);
		    break;
		case 4:
		    CategoriaUtil.deletarCategoria(service);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    }catch (InputMismatchException e) {
		System.out.println("Opção inexistente no momento");
	    } catch (CategoriaException e) {
		System.out.println(e.getMessage());
	    } catch (BackButtonException e) {
	    } catch (ProductoException e) {
		System.out.println(e.getMessage());
	    } catch (ListaVaziaException e) {
		System.out.println(e.getMessage());
	    } catch (NumberFormatException e) {
		System.out.println("Opção inválida. Tente digitar apenas números");
	    }
	} while (true);
    }

    // --------------------------- MENUS PRODUTO ----------------------------------
    public boolean telaProduto(Categoria cat) {
	ProductService service = new ProductService(cat);
	int opcao = -1;
	while (true) {
	    try {
		service.listarPordutos();
		Menu.menuProdutos();
		opcao = scan.nextInt();
		switch (opcao) {
		case 0:
		    return true;
		case 1:
		    ProdutosUtil.adicionarProduto(service, cat);
		    break;
		case 2:
		    while (this.menuEditarProduto(service))
			;
		    break;
		case 3:
		    Product p = ProdutosUtil.selecionarProduto(service, "Excluir");
		    service.deletar(p);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inválida! Tente novamente.");
		scan.next();
	    } catch (ProductoException e) {
		System.out.println(e.getMessage());
	    } catch (BackButtonException e) {
	    } catch (ListaVaziaException e) {
		System.out.println(e.getMessage());
		return createNewList(service, cat);
	    } catch (NumberFormatException e) {
		System.out.println("Tente digitar apenas números");
	    }
	}
    }

    // --------------------------- MENUS EDITAR PRODUTO
    // ----------------------------------
    private boolean menuEditarProduto(ProductService service) {
	scan.useDelimiter(System.lineSeparator());
	String opcaoEditarProduto;
	while (true) {
	    try {
		Product p = ProdutosUtil.selecionarProduto(service, "Editar");
		ButtonUtil.botaoVoltar(p);
		System.out.println("Escolha o que deseja editar");
		Menu.menuEditarProduto();
		opcaoEditarProduto = scan.next();
		if (Integer.parseInt(opcaoEditarProduto) == 0) {
		    return false;
		}
		switch (Integer.parseInt(opcaoEditarProduto)) {
		case 1:
		    ProdutosUtil.editarProduto(service, p);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		case 2:
		    System.out.print("Novo nome: ");
		    String name = scan.next();
		    service.editarNome(p, name);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		case 3:
		    System.out.print("Novo valor estipulado: R$");
		    double valorEstipulado = scan.nextDouble();
		    service.editarPrecoEstipulado(p, valorEstipulado);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		case 4:
		    ProdutosUtil.editarValorReal(service, p);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		case 5:
		    ProdutosUtil.marcarComoConcluido(service, p);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		case 6:
		    ProdutosUtil.marcarComoNaoConcluido(service, p);
		    if (Menu.naoContinuarEditando())
			return false;
		    break;
		default:
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inválida! Tente novamente.");
		scan.next();
	    } catch (BackButtonException e) {
		return false;
	    } catch (NumberFormatException e) {
		System.out.println("Digite apenas números");
	    }
	}

    }

    // ------------------------- MÉTODOS BÁSICOS --------------------------------

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

    private boolean createNewList(ProductService service, Categoria cat) {
	System.out.println("Adicionar novo produto a essa lista?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - não");
	try {
	    if (scan.nextInt() == 1) {
		ProdutosUtil.adicionarProduto(service, cat);
	    } else {
		return true;
	    }
	} catch (InputMismatchException e1) {
	    System.out.println("digite apenas números");
	} catch (BackButtonException e) {
	    return true;
	}
	return false;
    }

}
