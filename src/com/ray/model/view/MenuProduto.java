package com.ray.model.view;

import java.util.Scanner;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.BackButtonException;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.OpcaoInvalidaException;
import com.ray.model.exception.ProductoException;
import com.ray.model.interacoes.InteracaoProduto;
import com.ray.model.service.ProductService;
import com.ray.model.util.ButtonUtil;
import com.ray.model.util.ProdutosUtil;

public class MenuProduto {

    // --------------------- MENUS EDITAR PRODUTO ----------------------------
    /**
     * Exceptions tratadas: EntradaInvalidaException, NumberFormatException, OpcaoInvalidaException
     */
    
    @SuppressWarnings("resource")
    public static boolean menuEditarProduto(ProductService service) {
	String opcaoEditarProduto;
	Scanner scan = new Scanner(System.in);
	while (true) {
	    try {
		Product p = InteracaoProduto.selecionarProduto(service, "Editar");
		ButtonUtil.botaoVoltar(p);
		System.out.println("Escolha o que deseja editar");
		Menu.menuEditarProduto();
		opcaoEditarProduto = scan.next();
		if (opcaoEditarProduto.equals("0"))
		    return false;
		switch (Integer.parseInt(opcaoEditarProduto)) {
		case 1:
		    return eliminarVerbose((InteracaoProduto.editarTudoProduto(service, p)));
		case 2:
		    return eliminarVerbose(InteracaoProduto.editarNomeProduto(service, p));
		case 3:
		    return eliminarVerbose(InteracaoProduto.editarValorEstipulado(service, p));
		case 4:
		    return eliminarVerbose(InteracaoProduto.editarValorReal(service, p));
		case 5:
		    return eliminarVerbose(InteracaoProduto.marcarComoConcluido(service, p));
		case 6:
		    return eliminarVerbose(InteracaoProduto.marcarComoNaoConcluido(service, p));
		case 7:
		    return eliminarVerbose(InteracaoProduto.editarCategoria(p));
		default:
		    throw new OpcaoInvalidaException("Opção inválida");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Entrada inválida! Digite apenas números");
	    } catch (BackButtonException e) {
		return false;
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }catch (ProductoException e) {
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
	ProdutosUtil.mostrarInfosProdutos(cat.getUser(), service, cat.getOrcamento());
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
