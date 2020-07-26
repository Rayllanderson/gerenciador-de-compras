package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.BackButtonException;
import model.exception.ConfirmException;
import model.exception.ListaVaziaException;
import model.exception.ProductoException;
import model.service.ProductService;

public class ProdutosUtil {

    // ----------------------- MÉTODOS MENU PRINCIPAL ------------------------//
    /*
     * Métodos do menu principal não passará ConfirmException adiante
     * 
     */

    public static void adicionarProduto(ProductService service, Categoria cat)
	    throws InputMismatchException, ProductoException, BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Nome: ");
	String nome = scan.next();
	ButtonUtil.botaoVoltar(nome);
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	double valorEstipulado = scan.nextDouble();
	ButtonUtil.botaoVoltar(valorEstipulado);
	System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	System.out.print("Qual o preço que você realmente pagou? R$");
	double valorReal = scan.nextDouble();
	Product p = new Product(null, nome, valorEstipulado, valorReal, false, cat.getUser(), cat);
	if (!(valorReal == 0)) {
	    service.marcarComoConcluido(p, valorReal);
	}
	service.inserir(p);
    }

    public static void deletarProduto(ProductService service) throws NumberFormatException, ProductoException {
	Product p = ProdutosUtil.selecionarProduto(service, "Excluir");
	if (ButtonUtil.confirmar("deletar")) {
	    service.deletar(p);
	} else {
	    System.out.println("Produto não renomeado");
	}
    }

    // ---------------------- MÉTODOS EDITAR PRODUTO -------------------------//
    public static boolean editarTudoProduto(ProductService service, Product p) throws ConfirmException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Nome: ");
	String nome = scan.next();
	ButtonUtil.botaoVoltar(nome);
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	try {
	    double valorEstipulado = scan.nextDouble();
	    System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	    System.out.print("Qual o preço que você realmente pagou? R$");
	    Double valorReal = scan.nextDouble();
	    if (ButtonUtil.confirmar("confirmar as alterações")) {
		if (!(valorReal == 0)) {
		    service.marcarComoConcluido(p, valorReal);

		} else {
		    valorReal = null;
		}
		p.setNome(nome);
		p.setPrecoEstipulado(valorEstipulado);
		service.atualizar(p);
		return true;
	    } else
		throw new ConfirmException("Produto não alterado");
	} catch (InputMismatchException e) {
	    System.out.println("Valor inválido");
	    return false;
	} catch (ProductoException e) {
	    System.out.println(e.getMessage());
	    return false;
	}
    }

    public static boolean marcarComoConcluido(ProductService service, Product p)
	    throws NumberFormatException, ConfirmException, ProductoException, BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	boolean sucess = true;
	if (p.getPrecoReal() != 0) {
	    System.out.println("Pressione 0 para cancelar");
	    System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	    System.out.println("[ 1 ] - sim");
	    System.out.println("[ 2 ] - nop");
	    String n = scan.next();
	    ButtonUtil.botaoVoltar(n);
	    if (Integer.parseInt(n) == 1) {
		sucess = editarValorReal(service, p);
	    }
	}
	if (p.getPrecoReal() == null || p.getPrecoReal() == 0) {
	    System.out.println("Quanto pagou nesse produto?");
	    try {
		p.setPrecoReal(scan.nextDouble());
	    } catch (InputMismatchException e) {
		System.out.println("Valor inválido");
		return false;
	    }
	}
	double value = p.getPrecoReal();
	service.marcarComoConcluido(p, value);
	return sucess;
    }

    public static boolean marcarComoNaoConcluido(ProductService service, Product p)
	    throws NumberFormatException, ProductoException, BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	boolean sucess = true;
	if (p.getPrecoReal() != 0) {
	    System.out.println("Pressione 0 para cancelar");
	    System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	    System.out.println("[ 1 ] - sim");
	    System.out.println("[ 2 ] - nop");
	    String n = scan.next();
	    ButtonUtil.botaoVoltar(n);
	    if (Integer.parseInt(n) == 1) {
		sucess = editarValorReal(service, p);
	    }
	}
	double value = p.getPrecoReal();
	service.marcarComoNaoConcluido(p, value);
	return sucess;
    }

    public static boolean editarValorReal(ProductService service, Product p) throws ConfirmException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	try {
	    System.out.println("Pressione -1 para cancelar");
	    System.out.print("Valor real: R$");
	    double valorReal = scan.nextDouble();
	    ButtonUtil.botaoVoltar(valorReal);
	    if (ButtonUtil.confirmar("alterar o valor real")) {
		service.editarPrecoReal(p, valorReal);
		return true;
	    } else
		throw new ConfirmException("Valor não alterado.");
	} catch (InputMismatchException e) {
	    System.out.println("Digite um valor válido.");
	    return false;
	}catch (BackButtonException e) {
	    return false;
	}
    }

    public static boolean editarValorEstipulado(ProductService service, Product p) {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	double valorEstipulado = 0;
	try {
	    System.out.print("Novo valor estipulado: R$");
	    valorEstipulado = scan.nextDouble();
	    if (ButtonUtil.confirmar("alterar o valor real")) {
		service.editarPrecoEstipulado(p, valorEstipulado);
		return true;
	    } else {
		throw new ConfirmException("Valor não alterado.");
	    }
	} catch (InputMismatchException e) {
	    System.out.println("Digite um valor válido.");
	    return false;
	}catch (ConfirmException e) {
	   System.out.println("Valor não alterado.");
	   return false;
	}
    }

    public static void editarNomeProduto(ProductService service, Product p) throws ConfirmException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	System.out.print("Novo nome: ");
	String name = scan.next();
	if (ButtonUtil.confirmar("renomear")) {
	    service.editarNome(p, name);
	} else {
	    throw new ConfirmException("Produto não renomeado");
	}
    }

    // -------------------- MÉTODOS FUNÇÕES ÚTEIS ----------------------//
    public static void quantidadeGasta(ProductService service, Categoria cat) {
	try {
	    double orcamento = cat.getOrcamento();
	    double valorGasto = service.quantidadeGasta();
	    String complemento = ". ";
	    if (orcamento == 0) {
		complemento += "E você não tem um orcamento para essa lista";
	    } else {
		if (valorGasto > orcamento) {
		    complemento += "Você já ultrapassou seu orçamento em R$" + (valorGasto - orcamento);
		} else {
		    complemento += "Você ainda tem R$" + (orcamento - valorGasto)
			    + " para gastar de acordo com seu orçamento de R$" + orcamento;
		}
	    }
	    System.out.println("Você já gastou R$" + valorGasto + complemento);
	} catch (NullPointerException e) {
	    System.out.println("Você não tem orçamento para esta lista. Adicione um no menu principal");
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto comprado até o momento, sendo assim, você não gastou nada.");
	}

    }

    public static void disponivelParaComprar(ProductService service, Categoria cat) {
	try {
	    double disponivel = service.valorDisponivelParaCompra(cat);
	    String complemento = ". ";
	    if (cat.getOrcamento() == 0 || cat.getOrcamento() == null) {
		throw new NullPointerException();
	    }
	    if (disponivel < 0) {
		complemento += "Ixi! Você passou do seu orcamento em R$" + (-(disponivel));
		complemento += ". Você não tem mais nada disponível para gastar";
		complemento += ". Orçamento para lista " + cat.getName() + ": R$" + cat.getOrcamento();
	    } else {
		complemento = "Você tem disponível R$" + disponivel + ", de acordo com seu orçamento para lista "
			+ cat.getName();
	    }
	    System.out.println(complemento);
	} catch (NullPointerException e) {
	    System.out.println(
		    "Você não tem orçamento para esta lista, Portanto, impossível saber quanto ainda tem disponível para compra :( . Adicione um orçamento no menu principal");
	} catch (ListaVaziaException e) {
	    System.out.println("Você ainda não comprou nenhum produto da lista, portanto, não gastou nada");
	}
    }

    public static void valorEconomizado(ProductService service) {
	try {
	    double valorEconomizado = service.valorEconomizado();
	    if (valorEconomizado < 0) {
		System.out.println("Eita! Você não economizou nada! Valor gastou R$" + (-(valorEconomizado))
			+ " a mais do que planejava");
	    } else {
		System.out.println("Você economizou R$" + valorEconomizado + ", Parabéns!");
	    }
	} catch (ListaVaziaException e) {
	    System.out.println(
		    "Hmm, parece que você ainda não comprou nenhum produto da lista, portanto, impossível saber valor economizado :(");
	}
    }

    public static void listarNaoConcluidos(ProductService service) {
	try {
	    service.listarNaoConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Todos os produtos da lista foram comprados :)");
	}
    }

    public static void listarConcluidos(ProductService service) {
	try {
	    service.listarConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }

    // -----------------------------------------------------------------------------//

    public static Product selecionarProduto(ProductService service, String acao)
	    throws NumberFormatException, ProductoException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	service.listarPordutos();
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Esolha qual produto deseja " + acao + ": ");
	String produtoEscolhido = scan.next();
	ButtonUtil.botaoVoltar(produtoEscolhido);
	Product p = service.getProdutoByNumer(Integer.parseInt(produtoEscolhido));
	return p;
    }

}
