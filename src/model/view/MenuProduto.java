package model.view;

import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.BackButtonException;
import model.exception.EntradaInvalidaException;
import model.exception.OpcaoInvalidaException;
import model.service.ProductService;
import model.util.ButtonUtil;
import model.util.ProdutosUtil;

public class MenuProduto {

    // --------------------- MENUS EDITAR PRODUTO ----------------------------
    /**
    /**
     * Exceptions tratadas: EntradaInvalidaException, NumberFormatException, OpcaoInvalidaException
     */
    
    @SuppressWarnings("resource")
    public static boolean menuEditarProduto(ProductService service) {
	String opcaoEditarProduto;
	while (true) {
	    Scanner scan = new Scanner(System.in);
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
		    return eliminarVerbose((ProdutosUtil.editarTudoProduto(service, p)));
		case 2:
		    return eliminarVerbose(ProdutosUtil.editarNomeProduto(service, p));
		case 3:
		    return eliminarVerbose(ProdutosUtil.editarValorEstipulado(service, p));
		case 4:
		    return eliminarVerbose(ProdutosUtil.editarValorReal(service, p));
		case 5:
		    return eliminarVerbose(ProdutosUtil.marcarComoConcluido(service, p));
		case 6:
		    return eliminarVerbose(ProdutosUtil.marcarComoNaoConcluido(service, p));
		default:
		    throw new OpcaoInvalidaException("Opção inválida");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Entrada inválida! Digite apenas números");
	    } catch (BackButtonException e) {
		return false;
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    // ------------------------- MENU FUNCOES UTEIS -------------------------
    /**
     * Exceptions tratadas: EntradaInvalidaException, NumberFormatException
     */
    public static boolean funcoesUteis(ProductService service, Categoria cat) {
	String opcaoEditarProduto;
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
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
		    throw new EntradaInvalidaException("Opção inválida! Tente novamente.");
		}
	    } catch (EntradaInvalidaException e) {
		System.out.println(e.getMessage());
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
