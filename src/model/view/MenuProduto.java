package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.BackButtonException;
import model.exception.ConfirmException;
import model.service.ProductService;
import model.util.ButtonUtil;
import model.util.ProdutosUtil;

public class MenuProduto {

    private static Scanner scan = new Scanner(System.in);

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
		if (opcaoEditarProduto.equals("0"))
		    return false;
		switch (Integer.parseInt(opcaoEditarProduto)) {
		case 1:
		    eliminarVerbose(ProdutosUtil.editarTudoProduto(service, p));
		case 2:
		    ProdutosUtil.editarNomeProduto(service, p);
		    return Menu.continuarEditando();
		case 3:
		    eliminarVerbose(ProdutosUtil.editarValorEstipulado(service, p));
		case 4:
		   return eliminarVerbose(ProdutosUtil.editarValorReal(service, p));
		case 5:
		    return eliminarVerbose(ProdutosUtil.marcarComoConcluido(service, p)); 
		case 6:
		     return eliminarVerbose(ProdutosUtil.marcarComoNaoConcluido(service, p)); 
		default:
		    throw new InputMismatchException();
		}
	    } catch (NumberFormatException e) {
		System.out.println("Entrada inválida! Tente novamente.");
	    } catch (BackButtonException e) {
		return false;
	    } catch (ConfirmException e) {
		System.out.println(e.getMessage());
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
	    } catch (NumberFormatException e) {
		System.out.println("Digite apenas números");
	    }
	}
    }

    
    private static boolean eliminarVerbose(boolean funcao) {
	if (funcao) {
	    return Menu.continuarEditando();
	} else {
	    return false;
	}
    }

}
