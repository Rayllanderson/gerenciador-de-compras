package com.ray.model.view;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ray.informacoes.InformacoesProdutos;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.BackButtonException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.MyLoginException;
import com.ray.model.exception.OpcaoInvalidaException;
import com.ray.model.interacoes.InteracaoCategoria;
import com.ray.model.interacoes.InteracaoProduto;
import com.ray.model.interacoes.InteracaoUser;
import com.ray.model.service.CategoriaService;
import com.ray.model.service.ProductServiceConsole;
import com.ray.model.util.ProdutosUtilConsole;
import com.ray.model.util.UserUtil;

public class TelaPrincipal {

    private static Scanner scan = new Scanner(System.in);
    private UserDao telaLogin;

    public TelaPrincipal() {
	this.telaLogin = DaoFactory.createUserDao();
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
		String op = scan.next().trim();
		if (Integer.parseInt(op) == 1) {
		    username = InteracaoUser.pedirAlgo(scan, "Usuário");
		    password = InteracaoUser.pedirAlgo(scan, "Senha");
		    return telaLogin.login(username, password);
		} else if (Integer.parseInt(op) == 2) {
		    System.out.print("Nome: ");
		    String name = scan.next();
		    username = InteracaoUser.pedirAlgo(scan, "Usuário");
		    password = InteracaoUser.pedirAlgo(scan, "Senha");
		    if (this.telaLogin.cadastrar(new User(null, name, username, password))) {
			System.out.println("Cadastro realizado com sucesso! Faça login para continuar");
		    }
		} else {
		    throw new OpcaoInvalidaException("Digite uma opção válida");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Digite uma opcao válida");
	    } catch (MyLoginException e) {
		System.out.println(e.getMessage());
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }catch (RuntimeException e) {
		System.out.println("Ocorreu um erro inesperado");
		e.printStackTrace();
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
	    System.out.println("O que deseja fazer, " + UserUtil.formatarNome(user.getName()) + "?");
	    Menu.menuPrincipalCategorias();
	    try {
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    scan.close();
		    System.exit(0);
		case 1:
		    return InteracaoCategoria.selecionarCategoria(service, scan);
		case 2:
		    InteracaoCategoria.adicionarCategoria(service, user);
		    break;
		case 3:
		    MenuCategoria.editarCategoria(service);
		    break;
		case 4:
		    InteracaoCategoria.deletarCategoria(service);
		    break;
		case 5:
		    UserUtil.mostrarInfos(user);
		    MenuUser.opcoesAccount(user);
		    break;
		default:
		    throw new OpcaoInvalidaException("Opção inválida");
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inexistente no momento");
	    } catch (BackButtonException e) {
	    } catch (NumberFormatException e) {
		System.out.println("Opção inválida. Tente digitar apenas números");
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }catch (RuntimeException e) {
		System.out.println("Ocorreu um erro inesperado");
		e.printStackTrace();
	    }
	} while (true);
    }

    // --------------------------- MENUS PRODUTO ----------------------------------
    public boolean telaProduto(Categoria cat) {
	ProductServiceConsole service = new ProductServiceConsole(cat);
	String opcao = null;
	while (true) {
	    try {
		ProdutosUtilConsole util = new ProdutosUtilConsole(cat);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		InformacoesProdutos.somaTotalConsole(util);
		mostrarOrcamento(cat);
		util.listarPordutosConsole();
		Menu.menuPrincipalProdutos();
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    return true;
		case 1:
		    while (MenuProduto.funcoesUteis(util, cat))
			;
		    break;
		case 2:
		    InteracaoProduto.adicionarProduto(service, cat);
		    break;
		case 3:
		    while (MenuProduto.menuEditarProduto(service))
			;
		    break;
		case 4:
		    InteracaoProduto.deletarProduto(service);
		    break;
		case 5:
		    InteracaoCategoria.adicionarOrcamento(new CategoriaService(cat.getUser()), cat);
		    break;
		default:
		    throw new OpcaoInvalidaException("Opção inválida! Tente novamente.");
		}
	    } catch (BackButtonException e) {
	    } catch (NumberFormatException e) {
		System.out.println("Tente digitar apenas números");
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    } catch (ListaVaziaException e) {
		return MenuCategoria.createNewList(service, cat);
	    }catch (RuntimeException e) {
		System.out.println("Ocorreu um erro inesperado");
	    }
	}
    }

    // ------------------------- MÉTODOS BÁSICOS --------------------------------

    private void mostrarOrcamento(Categoria cat) {
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	String complemento = ": ";
	if (cat.getOrcamento() == 0 || cat.getOrcamento() == null) {
	    complemento += "Lista sem orçamento";
	} else {
	    complemento += currencyFormatter.format(cat.getOrcamento());
	}
	System.out.println("Orçamento para esta Lista (" + cat.getName() + ")" + complemento);
	System.out.println();
    }

    // TODO:FUÇAR OS BUGS RESTANTES
}
