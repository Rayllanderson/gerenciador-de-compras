package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.ListaVaziaException;
import model.service.ProductService;

public class ProdutosUtil {
    private static Scanner scan = new Scanner(System.in);

    public static void adicionarProduto(ProductService service, Categoria cat) {
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

    public static void editarProduto(ProductService service, Product p) throws InputMismatchException {
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Nome: ");
	String nome = scan.next();
	ButtonUtil.botaoVoltar(nome);
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	double valorEstipulado = scan.nextDouble();
	System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	System.out.print("Qual o preço que você realmente pagou? R$");
	Double valorReal = scan.nextDouble();
	if (!(valorReal == 0)) {
	    service.marcarComoConcluido(p, valorReal);
	} else {
	    valorReal = null;
	}
	p.setNome(nome);
	p.setPrecoEstipulado(valorEstipulado);
	service.atualizar(p);
    }

    public static void marcarComoConcluido(ProductService service, Product p) {
	if (p.getPrecoReal() != 0) {
	    System.out.println("Pressione 0 para cancelar");
	    System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	    System.out.println("[ 1 ] - sim");
	    System.out.println("[ 2 ] - nop");
	    String n = scan.next();
	    ButtonUtil.botaoVoltar(n);
	    if (Integer.parseInt(n) == 1) {
		editarValorReal(service, p);
	    }
	}
	if (p.getPrecoReal() == null || p.getPrecoReal() == 0) {
	    System.out.println("Quanto pagou nesse produto?");
	    p.setPrecoReal(scan.nextDouble());
	}
	double value = p.getPrecoReal();
	service.marcarComoConcluido(p, value);
    }

    public static void marcarComoNaoConcluido(ProductService service, Product p) {
	if (p.getPrecoReal() != 0) {
	    System.out.println("Pressione 0 para cancelar");
	    System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	    System.out.println("[ 1 ] - sim");
	    System.out.println("[ 2 ] - nop");
	    String n = scan.next();
	    ButtonUtil.botaoVoltar(n);
	    if (Integer.parseInt(n) == 1) {
		editarValorReal(service, p);
	    }
	}
	double value = p.getPrecoReal();
	service.marcarComoNaoConcluido(p, value);
    }

    public static void editarValorReal(ProductService service, Product p) {
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Valor real: R$");
	double valorReal = scan.nextDouble();
	ButtonUtil.botaoVoltar(valorReal);
	service.editarPrecoReal(p, valorReal);
    }

    public static Product selecionarProduto(ProductService service, String acao) throws NumberFormatException {
	service.listarPordutos();
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Esolha qual produto deseja " + acao + ": ");
	String produtoEscolhido = scan.next();
	ButtonUtil.botaoVoltar(produtoEscolhido);
	Product p = service.getProdutoByNumer(Integer.parseInt(produtoEscolhido));
	return p;
    }

    public static void quantidadeGasta(ProductService service, Categoria cat) {
	try {
	    double orcamento = cat.getOrcamento();
	    double valorGasto = service.quantidadeGasta();
	    String complemento = ". ";
	    if (orcamento == 0) {
		complemento += "Você não tem um orcamento para essa lista";
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
	}catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto comprado até o momento, sendo assim, você não gastou nada.");
	}

    }

    public static void disponivelParaComprar(ProductService service, Categoria cat) {
	try {
	    double disponivel = service.valorDisponivelParaCompra(cat);
	    double valorGasto = service.quantidadeGasta();
	    String complemento = ". ";
	    if (cat.getOrcamento() == 0 || cat.getOrcamento() == null) {
		throw new NullPointerException();
	    }
	    if (disponivel < 0) {
		complemento += "Ixi! Você passou do seu orcamento em R$" + (-(disponivel));
		complemento += ". Você não tem mais nada disponível para gastar";
		complemento += ". Orçamento para lista " + cat.getName() + ": R$" + cat.getOrcamento();
	    } else {
		complemento = " e, de acordo com seu orçamento para lista " + cat.getName()
			+ ", você ainda tem disponível R$" + disponivel;
	    }
	    System.out.println("Você já gastou R$" + valorGasto + complemento);
	} catch (NullPointerException e) {
	    System.out.println(
		    "Você não tem orçamento para esta lista, Portanto, impossível saber quanto ainda tem disponível para compra :( . Adicione um orçamento no menu principal");
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
	    System.out.println("Hmm, parece que você ainda não comprou nenhum produto da lista, portanto, impossível saber valor economizado :(");
	}
    }
    
    public static void listarNaoConcluidos(ProductService service) {
	try {
	    service.listarNaoConcluidos();
	}catch (ListaVaziaException e) {
	    System.out.println("Todos os produtos da lista foram comprados :)");
	}
    }
    
    public static void listarConcluidos(ProductService service) {
	try {
	    service.listarConcluidos();
	}catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }

}
