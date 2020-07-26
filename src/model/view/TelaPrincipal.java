package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TelaLoginDao;
import model.entities.Categoria;
import model.entities.User;
import model.exception.BackButtonException;
import model.exception.EntradaInvalidaException;
import model.exception.MyLoginException;
import model.exception.OpcaoInvalidaException;
import model.service.CategoriaService;
import model.service.ProductService;
import model.util.CategoriaUtil;
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

    // -------------------- MENUS CATEGORIA -----------------------------
    @SuppressWarnings("resource")
    public Categoria telaCategoria(User user) {
	CategoriaService service = new CategoriaService(user);
	do {
	    Scanner scan = new Scanner(System.in);
	    String opcao = null;
	    System.out.println("O que deseja fazer, " + this.formatarNome(user.getName()) + "?");
	    Menu.menuPrincipalCategorias();
	    try {
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    scan.close();
		    System.exit(0);
		case 1:
		   return CategoriaUtil.selecionarCategoria(service, scan);
		case 2:
		    CategoriaUtil.adicionarCategoria(service, user);
		    break;
		case 3:
		    MenuCategoria.editarCategoria(service);
		    break;
		case 4:
		    CategoriaUtil.deletarCategoria(service);
		    break;
		default:
		    throw new OpcaoInvalidaException("Opção inválida");
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inexistente no momento");
	    } catch (BackButtonException e) {
	    } catch (NumberFormatException e) {
		System.out.println("Opção inválida. Tente digitar apenas números");
	    }catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }
	} while (true);
    }

    // --------------------------- MENUS PRODUTO ----------------------------------
    public boolean telaProduto(Categoria cat) {
	ProductService service = new ProductService(cat);
	String opcao = null;
	while (true) {
	    try {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		mostrarOrcamento(cat);
		service.listarPordutos();
		Menu.menuPrincipalProdutos();
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    return true;
		case 1:
		    while (MenuProduto.funcoesUteis(service, cat))
			;
		    break;
		case 2:
		    ProdutosUtil.adicionarProduto(service, cat);
		    break;
		case 3:
		    while (MenuProduto.menuEditarProduto(service))
			;
		    break;
		case 4:
		    ProdutosUtil.deletarProduto(service);
		    break;
		case 5:
		    CategoriaUtil.adicionarOrcamento(new CategoriaService(cat.getUser()), cat);
		    break;
		default:
		    throw new EntradaInvalidaException("Opção inválida! Tente novamente.");
		}
	    } catch (BackButtonException e) {
	    }catch (NumberFormatException e) {
		System.out.println("Tente digitar apenas números");
	    } catch (EntradaInvalidaException e) {
		System.out.println(e.getMessage());
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

    private void mostrarOrcamento(Categoria cat) {
	System.out.println();
	String complemento = ": ";
	if (cat.getOrcamento() == 0 || cat.getOrcamento() == null) {
	    complemento += "Lista sem orçamento";
	} else {
	    complemento += "R$" + cat.getOrcamento();
	}
	System.out.println("Orçamento para esta Lista" + complemento);
	System.out.println();
    }

    // TODO: CONFIRMAR EXLCUIR, ADICIONAR MUDAR ORÇAMETO NO MENU PRODUTO, FUÇAR OS
    // BUGS RESTANTES E FIM! BUGS ELIMINADOS COM SUCESSO!

}
