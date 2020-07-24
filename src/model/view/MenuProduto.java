package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.BackButtonException;
import model.exception.ListaVaziaException;
import model.service.ProductService;
import model.util.ButtonUtil;
import model.util.ProdutosUtil;

public class MenuProduto {
    
    private static Scanner scan = new Scanner (System.in);
    
    // --------------------- MENUS EDITAR PRODUTO ----------------------------
    public static boolean menuEditarProduto(ProductService service) {
	String opcaoEditarProduto;
	while (true) {
	    try {
		Product p = ProdutosUtil.selecionarProduto(service, "Editar");
		ButtonUtil.botaoVoltar(p);
		System.out.println("Escolha o que deseja editar");
		Menu.menuEditarProduto();
		opcaoEditarProduto = scan.next();
		if (opcaoEditarProduto.equals("0")) {
		    return false;
		}
		switch (Integer.parseInt(opcaoEditarProduto)) {
		case 1:
		    ProdutosUtil.editarProduto(service, p);
		    return Menu.continuarEditando();
		case 2:
		    System.out.print("Novo nome: ");
		    String name = scan.next();
		    service.editarNome(p, name);
		    return Menu.continuarEditando();
		case 3:
		    System.out.print("Novo valor estipulado: R$");
		    double valorEstipulado = scan.nextDouble();
		    service.editarPrecoEstipulado(p, valorEstipulado);
		    return Menu.continuarEditando();
		case 4:
		    ProdutosUtil.editarValorReal(service, p);
		    return Menu.continuarEditando();
		case 5:
		    ProdutosUtil.marcarComoConcluido(service, p);
		    return Menu.continuarEditando();
		case 6:
		    ProdutosUtil.marcarComoNaoConcluido(service, p);
		    return Menu.continuarEditando();
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
    
    // ------------------------- MENU FUNCOES UTEIS -------------------------
    public static boolean funcoesUteis(ProductService service, Categoria cat) {
	String opcaoEditarProduto;
	while (true) {
	    try {
		Menu.menuFinanceiro();
		opcaoEditarProduto = scan.next();
		if (opcaoEditarProduto.equals("0")) {
		    return false;
		}
		switch (Integer.parseInt(opcaoEditarProduto)) {
		case 1:
		    ProdutosUtil.quantidadeGasta(service, cat);
		    break;
		case 2:
		    System.out.println("Produtos comprados: ");
		    ProdutosUtil.listarConcluidos(service);
		    break;
		case 3:
		    System.out.println("Produdos que você ainda não comprou: ");
		    ProdutosUtil.listarNaoConcluidos(service);
		    break;
		case 4:
		    ProdutosUtil.disponivelParaComprar(service, cat);
		    break;
		case 5:
		    ProdutosUtil.valorEconomizado(service);
		    break;
		default:
		    throw new InputMismatchException();
		}
	    } catch (InputMismatchException e) {
		System.out.println("Opção inválida! Tente novamente.");
		scan.next();
	    } catch (NumberFormatException e) {
		System.out.println("Digite apenas números");
	    } catch (ListaVaziaException e) {
		
	    }
	}
    }

}
