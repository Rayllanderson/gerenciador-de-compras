package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.User;
import model.exception.BackButtonException;
import model.exception.CategoriaException;
import model.exception.ConfirmException;
import model.exception.EntradaInvalidaException;
import model.exception.ListaVaziaException;
import model.exception.OpcaoInvalidaException;
import model.service.CategoriaService;

public class CategoriaUtil {
    
    /**
     * @return categoria selecionada
     * @throws BackButtonException
     * @throws NumberFormatException
     * @throws OpcaoInvalidaException
     * @apiNote Exceptions tratadas nesse método: CategoriaException,
     *          ListaVaziaException
     */
    public static Categoria selecionarCategoria(CategoriaService service, Scanner scan)
	    throws BackButtonException, NumberFormatException, OpcaoInvalidaException {
	System.out.println("Pressione 0 para voltar");
	try {
	    service.ListarCategorias();
	    String num = scan.next();
	    ButtonUtil.botaoVoltar(num);
	    return service.getCategoriaByNumber(Integer.parseInt(num));
	} catch (ListaVaziaException e) {
	    throw new OpcaoInvalidaException(e.getMessage());
	} catch (CategoriaException e) {
	    throw new OpcaoInvalidaException(e.getMessage());
	}
    }

    /**
     * Exceptions tratados nesse método: EntradaInvalidaException
     * 
     * @throws BackButtonException
     * @throws NumberFormatException
     */
    public static void adicionarCategoria(CategoriaService service, User user)
	    throws BackButtonException, NumberFormatException {
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	try {
	    Categoria novaCategoria = new Categoria();
	    System.out.println("*-*-*-*- Criando nova Lista *-*-*-*-");
	    String nome = getNome(scan);
	    novaCategoria.setName(nome);
	    novaCategoria.setUser(user);
	    if (service.criarCategoria(novaCategoria)) {
		System.out.println("Deseja adicionar um orçamento para essa Lista?");
		System.out.println("[ 1 ] - sim");
		System.out.println("[ 2 ] - não");
		String op = scan.next();
		if (Integer.parseInt(op) == 1) {
		    inserirOrcamento(service, novaCategoria);
		}
		System.out.println("Nova lista criada! Selecione a lista no menu principal");
	    }
	} catch (EntradaInvalidaException e) {
	    System.out.println(e.getMessage());
	}
    }

    /**
     * {@summary Método pra perguntar o nome e receber nome de volta<br>
     * <br>
     * }
     * 
     * @param scan
     * @return nome digitado
     * @throws BackButtonException
     */
    private static String getNome(Scanner scan) throws BackButtonException {
	System.out.println("Pressione 0 para voltar");
	System.out.print("Nome: ");
	String nome = scan.next();
	ButtonUtil.botaoVoltar(nome);
	return nome;
    }

    /**
     * @throws NumberFormatException
     * @throws BackButtonException
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException,
     *          CategoriaException, ConfirmException
     */
    public static void deletarCategoria(CategoriaService service) throws NumberFormatException, BackButtonException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	try {
	    System.out.println("Selecione a lista que deseja Excluir: ");
	    System.out.println("Pressione 0 para voltar");
	    service.ListarCategorias();
	    String num = scan.next();
	    ButtonUtil.botaoVoltar(num);
	    Categoria cat1 = service.getCategoriaByNumber(Integer.parseInt(num));
	    ButtonUtil.confirmar("deletar a lista " + cat1.getName());
	    if (service.deletarCategoria(cat1))
		System.out.println("Lista deletada com sucesso!");
	} catch (ListaVaziaException e) {
	    System.out.println(e.getMessage());
	} catch (CategoriaException e) {
	    System.out.println(e.getMessage());
	} catch (ConfirmException e) {
	    System.out.println("Lista não deletada.");
	}
    }

    /**
     * @throws EntradaInvalidaException já com mensagem pronta
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException,
     *          CategoriaException, ConfirmException
     */
    private static void inserirOrcamento(CategoriaService service, Categoria cat) throws EntradaInvalidaException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	try {
	    System.out.print("Valor do orçamento para a lista " + cat.getName() + ": ");
	    double value = scan.nextDouble();
	    service.inserirOrcamento(cat, value);
	} catch (InputMismatchException e) {
	    throw new EntradaInvalidaException("Digite um valor válido");
	}
    }

    /**
     * @param oqVaiSerEditado exemplo: "nome" editará o nome. <br>
     *                        <br>
     *                        Opções dispoíveis para editar: <Strong>nome,
     *                        orcamento, tudo<Strong><br>
     *                        <br>
     * @throws BackButtonException
     * @throws NumberFormatException
     * @apiNote Exceptions tratadas nesse método: EntradaInvalidaException,
     *          ConfirmException
     */
    public static void editarTudoCategoria(CategoriaService service, String oqVaiSerEditado, Categoria cat)
	    throws BackButtonException, NumberFormatException {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	try {
	    if (oqVaiSerEditado.equalsIgnoreCase("nome")) {
		String name;
		System.out.print("Novo nome: ");
		name = scan.next();
		ButtonUtil.botaoVoltar(name);
		ButtonUtil.confirmar("renomear");
		cat.setName(name);
	    }

	    if (oqVaiSerEditado.equalsIgnoreCase("orcamento")) {
		inserirOrcamento(service, cat);
	    }
	    if (oqVaiSerEditado.equalsIgnoreCase("tudo")) {
		String name;
		System.out.print("Novo nome: ");
		name = scan.next();
		ButtonUtil.botaoVoltar(name);
		ButtonUtil.confirmar("alterar os valores");
		cat.setName(name);
		inserirOrcamento(service, cat);
	    }
	    if (service.atualizarCategoria(cat))
		System.out.println("Lista editada com sucesso!");
	    else
		System.out.println("Ocorreu um erro ao editar. Tente novamente");
	} catch (ConfirmException e) {
	    System.out.println("Lista não atualizada.");
	} catch (EntradaInvalidaException e) {
	    System.out.println(e.getMessage());
	}
    }

    /**
     * @param cat
     * @apiNote Exceptions tratadas nesse método: EntradaInvalidaException
     */
    public static void adicionarOrcamento(CategoriaService service, Categoria cat) {
	try {
	    inserirOrcamento(service, cat);
	} catch (EntradaInvalidaException e) {
	    System.out.println(e.getMessage());
	}
    }

}
