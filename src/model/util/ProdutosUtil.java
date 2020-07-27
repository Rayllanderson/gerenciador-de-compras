package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.exception.BackButtonException;
import model.exception.ConfirmException;
import model.exception.EntradaInvalidaException;
import model.exception.ListaVaziaException;
import model.exception.ProductoException;
import model.service.ProductService;

public class ProdutosUtil {

    // ----------------------- MÉTODOS MENU PRINCIPAL ------------------------//

    /**
     * @apiNote Exceptions tratadas neste método: EntradaInvalidaException
     * @return TRUE caso dê tudo certo FALSE caso de algo errado
     * @throws BackButtonException
     */
    public static boolean adicionarProduto(ProductService service, Categoria cat) throws BackButtonException {
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	System.out.println("Pressione 0 para cancelar");
	System.out.print("Nome: ");
	try {
	    String nome = scan.next();
	    ButtonUtil.botaoVoltar(nome);
	    double valorEstipulado = adicionarEditarValorEstiupulado(scan);
	    ButtonUtil.botaoVoltar(valorEstipulado);
	    double valorReal = adicionarEditarValorReal(scan);
	    Product p = new Product(null, nome, valorEstipulado, valorReal, false, cat.getUser(), cat);
	    concluir(valorReal, service, p);
	    return service.inserir(p);
	} catch (EntradaInvalidaException e) {
	    System.out.println(e.getMessage());
	}
	return false;
    }

    /**
     * @param service
     * @apiNote Exceptions tratadas neste método: ConfirmException,
     *          ProductoException, ListaVaziaException
     * @throws NumberFormatException terá que escolher produto nesse método, logo...
     * @throws BackButtonException
     */
    public static void deletarProduto(ProductService service) throws NumberFormatException, BackButtonException {
	try {
	    Product p = ProdutosUtil.selecionarProduto(service, "Excluir");
	    ButtonUtil.confirmar("deletar");
	    service.deletar(p);
	} catch (ConfirmException e) {
	    System.out.println("Produto não renomeado");
	} catch (ProductoException e) {
	    System.out.println(e.getMessage());
	} catch (ListaVaziaException e) {
	    System.out.println(e.getMessage());
	}
    }

    // ---------------------- MÉTODOS EDITAR PRODUTO -------------------------//
    /**
     * @apiNote Exceptions tratadas neste método: ConfirmException,
     *          EntradaInvalidaException
     * @return TRUE caso dê tudo certo FALSE caso de algo errado
     * @throws NumberFormatException
     * @throws BackButtonException
     */
    public static boolean editarTudoProduto(ProductService service, Product p)
	    throws NumberFormatException, BackButtonException {
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	try {
	    System.out.println("Pressione 0 para cancelar");
	    System.out.print("Nome: ");
	    String nome = scan.next();
	    ButtonUtil.botaoVoltar(nome);
	    double valorEstipulado = adicionarEditarValorEstiupulado(scan);
	    double valorReal = adicionarEditarValorReal(scan);
	    if (ButtonUtil.confirmar("confirmar as alterações")) {
		concluir(valorReal, service, p);
		p.setNome(nome);
		p.setPrecoEstipulado(valorEstipulado);
		return service.atualizar(p);
	    }
	} catch (ConfirmException e) {
	    System.out.println("Produto não alterado");
	} catch (EntradaInvalidaException e) {
	    System.out.println(e.getMessage());
	}
	return false;
    }

    /**
     * @apiNote Exceptions tratadas neste método: InputMismatchException,
     * @return TRUE se tudo der certo FALSE se algo der errado
     * @throws NumberFormatException caso digite uma letra em vez de número
     * @throws BackButtonException
     */
    public static boolean marcarComoConcluido(ProductService service, Product p)
	    throws NumberFormatException, BackButtonException {
	Scanner scan = new Scanner(System.in);
	boolean sucess = true;
	try {
	    sucess = alterarValor(p.getPrecoReal() != 0, scan, p, service);
	    if (p.getPrecoReal() == null || p.getPrecoReal() == 0) {
		System.out.println("Quanto pagou nesse produto?");
		p.setPrecoReal(scan.nextDouble());
	    }
	    double value = p.getPrecoReal();
	    service.marcarComoConcluido(p, value);
	    return sucess;
	} catch (InputMismatchException e) {
	    System.out.println("Valor inválido");
	}
	return false;
    }

    /**
     * @apiNote Exceptions tratadas neste método: Nenhuma
     * @return TRUE se tudo der certo FALSE se algo der errado
     * @throws NumberFormatException caso digite uma letra em vez de número
     * @throws BackButtonException   como nao trato nada aqui, passo adiante
     */
    public static boolean marcarComoNaoConcluido(ProductService service, Product p)
	    throws NumberFormatException, BackButtonException {
	Scanner scan = new Scanner(System.in);
	boolean sucess = true;
	sucess = alterarValor(p.getPrecoReal() != 0, scan, p, service);
	double value = p.getPrecoReal();
	service.marcarComoNaoConcluido(p, value);
	return sucess;
    }

    /**
     * @return true se obtiver sucesso! false se algo der errado
     * @apiNote Exceptions tratadas nesse método: InputMismatchException,
     *          ConfirmException
     * @throws BackButtonException
     */
    public static boolean editarValorReal(ProductService service, Product p) throws BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	try {
	    System.out.println("Pressione -1 para cancelar");
	    System.out.print("Valor real: R$");
	    double valorReal = scan.nextDouble();
	    if (valorReal == (int) -1) {
		ButtonUtil.botaoVoltar(0);
	    }
	    ButtonUtil.confirmar("alterar o valor real");
	    service.editarPrecoReal(p, valorReal);
	    return true;
	} catch (InputMismatchException e) {
	    System.out.println("Digite um valor válido.");
	} catch (ConfirmException e) {
	    System.out.println("Valor não alterado.");
	}
	return false;
    }

    /**
     * @return true se obtiver sucesso! false se algo der errado
     * @apiNote Exceptions tratadas nesse método: InputMismatchException,
     *          ConfirmException
     * @throws BackButtonException
     */
    public static boolean editarValorEstipulado(ProductService service, Product p) throws BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	double valorEstipulado = 0;
	try {
	    System.out.println("Pressione -1 para cancelar");
	    System.out.print("Novo valor estipulado: R$");
	    valorEstipulado = scan.nextDouble();
	    if (valorEstipulado == (int) -1) {
		ButtonUtil.botaoVoltar(0);
	    }
	    ButtonUtil.confirmar("alterar o valor real");
	    service.editarPrecoEstipulado(p, valorEstipulado);
	    return true;
	} catch (InputMismatchException e) {
	    System.out.println("Digite um valor válido.");
	} catch (ConfirmException e) {
	    System.out.println("Valor não alterado.");
	} catch (BackButtonException e) {
	    // TODO: handle exception
	}
	return false;
    }

    /**
     * @return FALSE caso não deseje renomear
     * @apiNote Exceptions tratadas: _ConfirmException_
     */
    public static boolean editarNomeProduto(ProductService service, Product p) {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	System.out.print("Novo nome: ");
	String name = scan.next();
	try {
	    ButtonUtil.confirmar("renomear");
	    service.editarNome(p, name);
	    return true;
	} catch (ConfirmException e) {
	    System.out.println("Produto " + p.getNome() + " não renomeado");
	}
	return false;
    }

    // -------------------- MÉTODOS FUNÇÕES ÚTEIS ----------------------//
    /**
     * @apiNote Exceptions tratadas nesse método: NullPointerException,
     *          ListaVaziaException
     */
    public static void quantidadeGasta(ProductService service, Categoria cat) {
	try {
	    double orcamento = cat.getOrcamento();
	    double valorGasto = service.valorRealGasto();
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

    /**
     * @apiNote Exceptions tratadas nesse método: NullPointerException,
     *          ListaVaziaException
     */
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
	    System.out.println("Você ainda não comprou nenhum produto da lista, portanto, ainda tem R$"
		    + cat.getOrcamento() + " para gastar");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void valorEconomizado(ProductService service) {
	try {
	    double valorEconomizado = service.valorEconomizado();
	    if (valorEconomizado < 0) {
		System.out.println("Eita! Você não economizou nada! Você gastou R$" + (-(valorEconomizado))
			+ " a mais do que planejava");
	    } else {
		System.out.println("Você economizou R$" + valorEconomizado + ", Parabéns!");
	    }
	} catch (ListaVaziaException e) {
	    System.out.println(
		    "Hmm, parece que você ainda não comprou nenhum produto da lista, portanto, impossível saber valor economizado :(");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void listarNaoConcluidos(ProductService service) {
	try {
	    service.listarNaoConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Todos os produtos da lista foram comprados :)");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void listarConcluidos(ProductService service) {
	try {
	    service.listarConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }

    // --------------------------------------------------------------------------------//

    /**
     * @apiNote Exceptions tratadas nesse método: Nenhum
     *
     * @return produto escolhido
     * 
     * @throws Observação            TODAS AS EXCEPTIONS JÁ POSSUIEM MENSAGEM,
     *                               APENAS USE O e.getMessage();
     * @throws NumberFormatException caso digite uma letra em vez de número
     * @throws ProductoException     caso não exista nenhum produto com número
     *                               escolhido
     * @throws ListaVaziaException
     * @throws BackButtonException
     */
    public static Product selecionarProduto(ProductService service, String acao)
	    throws NumberFormatException, ProductoException, ListaVaziaException, BackButtonException {
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

    // ------------------------- métodos privados -------------------------------//

    /**
     * @apiNote Exceptions tratadas nesse método: InputMismatchException
     */
    private static Double adicionarEditarValorEstiupulado(Scanner scan) throws EntradaInvalidaException {
	System.out.print("Qual o preço que você acha que vai pagar? R$");
	try {
	    return scan.nextDouble();
	} catch (InputMismatchException e) {
	    throw new EntradaInvalidaException("Digite um valor válido");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: InputMismatchException
     */
    private static Double adicionarEditarValorReal(Scanner scan) throws EntradaInvalidaException {
	System.out.println("OBS: se você não comprou o produto ainda, deixe 0");
	System.out.print("Qual o preço que você realmente pagou? R$");
	try {
	    return scan.nextDouble();
	} catch (InputMismatchException e) {
	    throw new EntradaInvalidaException("Valor inválido");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: Nenhuma
     * @param valorReal - valor real podendo ser qualquer, mas caso seja 0, deixa
     *                  null eliminar verbose apenas. se o valor real for
     *                  "diferente" de 0, entao ele marca como concluido, senao
     *                  deixa null { service.marcarComoConcluido(p, valorReal);
     * 
     * @param service   - ProductService service
     * 
     * @param p         - Product p
     */
    private static void concluir(Double valorReal, ProductService service, Product p) {
	if (!(valorReal == 0)) {
	    service.marcarComoConcluido(p, valorReal);
	} else {
	    valorReal = null;
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: Nenhuma pergunta se quer alterar o
     *          Valor caso a função seja verdadeira
     * 
     * @param funcao caso a função seja verdadeira, pergunta se quer alterar o
     *               valorReal
     * @throws NumberFormatException
     * @throws BackButtonException
     * @return TRUE CASO QUEIRA ALTERAR O VALOR OU ESCOLHA QUE NÃO, FALSE SE ALGO
     *         DER ERRADO
     */
    private static boolean alterarValor(boolean funcao, Scanner scan, Product p, ProductService service)
	    throws NumberFormatException, BackButtonException {
	boolean sucess = true;
	if (funcao) {
	    String n = perguntarAlterarValorReal(scan, p);
	    ButtonUtil.botaoVoltar(n);
	    if (Integer.parseInt(n) == 1) {
		sucess = editarValorReal(service, p);
	    }
	}
	return sucess;
    }

    /**
     * @apiNote Exceptions tratadas nesse método: nenhuma
     */
    private static String perguntarAlterarValorReal(Scanner scan, Product p) {
	System.out.println("Pressione 0 para cancelar");
	System.out.println("O preço real atual é de R$" + p.getPrecoReal() + ". Deseja alterar esse valor?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - nop");
	return scan.next();
    }

    public static void mostrarSomaTotal() {

    }
}
