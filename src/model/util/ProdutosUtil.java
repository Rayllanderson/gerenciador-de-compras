package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
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
	}else {
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

    public static Product selecionarProduto(ProductService service, String acao) throws NumberFormatException{
	service.listarPordutos();
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Esolha qual produto deseja " + acao + ": ");
	String produtoEscolhido = scan.next();
	ButtonUtil.botaoVoltar(produtoEscolhido);
	Product p = service.getProdutoByNumer(Integer.parseInt(produtoEscolhido));
	return p;
    }
}
