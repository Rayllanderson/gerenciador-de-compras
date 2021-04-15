package model.util;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.entities.Product;
import model.entities.User;
import model.exception.BackButtonException;
import model.exception.CategoriaException;
import model.exception.ConfirmException;
import model.exception.EntradaInvalidaException;
import model.exception.ListaVaziaException;
import model.exception.ProductoException;
import model.service.CategoriaService;
import model.service.ProductService;

public class ProdutosUtil {

    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    // ----------------------- M�TODOS MENU PRINCIPAL ------------------------//

    /**
     * @return TRUE caso d� tudo certo FALSE caso de algo errado
     *
     * @throws BackButtonException
     * @apiNote Exceptions tratadas neste m�todo: EntradaInvalidaException
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
     *
     * @throws NumberFormatException ter� que escolher produto nesse m�todo, logo...
     * @throws BackButtonException
     * @apiNote Exceptions tratadas neste m�todo: ConfirmException,
     * ProductoException, ListaVaziaException
     */
    public static void deletarProduto(ProductService service) throws NumberFormatException, BackButtonException {
        try {
            Product p = ProdutosUtil.selecionarProduto(service, "Excluir");
            ButtonUtil.confirmar("deletar o produto " + p.getNome());
            service.deletar(p);
        } catch (ConfirmException e) {
            System.out.println("Produto n�o deletado");
        } catch (ProductoException e) {
            System.out.println(e.getMessage());
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------------------- M�TODOS EDITAR PRODUTO -------------------------//

    /**
     * @return TRUE caso d� tudo certo FALSE caso de algo errado
     *
     * @throws NumberFormatException
     * @throws BackButtonException
     * @apiNote Exceptions tratadas neste m�todo: ConfirmException,
     * EntradaInvalidaException
     */
    public static boolean editarTudoProduto(ProductService service, Product p) throws NumberFormatException, BackButtonException {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
        try {
            System.out.println("Pressione 0 para cancelar");
            System.out.print("Nome: ");
            String nome = scan.next();
            ButtonUtil.botaoVoltar(nome);
            double valorEstipulado = adicionarEditarValorEstiupulado(scan);
            double valorReal = adicionarEditarValorReal(scan);
            if (ButtonUtil.confirmar("confirmar as altera��es")) {
                concluir(valorReal, service, p);
                p.setNome(nome);
                p.setPrecoEstipulado(valorEstipulado);
                return service.atualizar(p);
            }
        } catch (ConfirmException e) {
            System.out.println("Produto n�o alterado");
        } catch (EntradaInvalidaException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * @return TRUE se tudo der certo FALSE se algo der errado
     *
     * @throws NumberFormatException caso digite uma letra em vez de n�mero
     * @throws BackButtonException
     * @apiNote Exceptions tratadas neste m�todo: InputMismatchException,
     */
    public static boolean marcarComoConcluido(ProductService service, Product p) throws NumberFormatException, BackButtonException {
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
            System.out.println("Valor inv�lido");
        }
        return false;
    }

    /**
     * @return TRUE se tudo der certo FALSE se algo der errado
     *
     * @throws NumberFormatException caso digite uma letra em vez de n�mero
     * @throws BackButtonException como nao trato nada aqui, passo adiante
     * @apiNote Exceptions tratadas neste m�todo: Nenhuma
     */
    public static boolean marcarComoNaoConcluido(ProductService service, Product p) throws NumberFormatException, BackButtonException {
        Scanner scan = new Scanner(System.in);
        boolean sucess = true;
        sucess = alterarValor(p.getPrecoReal() != 0, scan, p, service);
        double value = p.getPrecoReal();
        service.marcarComoNaoConcluido(p, value);
        return sucess;
    }

    /**
     * @return true se obtiver sucesso! false se algo der errado
     *
     * @throws BackButtonException
     * @apiNote Exceptions tratadas nesse m�todo: InputMismatchException,
     * ConfirmException
     */
    public static boolean editarValorReal(ProductService service, Product p) throws BackButtonException {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Pressione -1 para cancelar");
            System.out.print("Valor real: R$");
            double valorReal = scan.nextDouble();
            if (valorReal == (int) -1) {
                ButtonUtil.botaoVoltar(0);
            }
            if (!p.isComprado()) {
                System.out.println("Produto ainda n�o foi comprado. Deseja marcar como comprado?");
                System.out.println("[ 1 ] - Sim");
                System.out.println("[ 2 ] - N�o");
                String op = scan.next();
                if (Integer.parseInt(op) == 1) {
                    service.marcarComoConcluido(p, valorReal);
                } else {

                }
            }
            ButtonUtil.confirmar("alterar o valor real do produto " + p.getNome() + " para " + currencyFormatter.format(valorReal));
            service.editarPrecoReal(p, valorReal);
            return true;
        } catch (InputMismatchException e) {
            System.out.println("Digite um valor v�lido.");
        } catch (ConfirmException e) {
            System.out.println("Valor n�o alterado.");
        }
        return false;
    }

    /**
     * @return true se obtiver sucesso! false se algo der errado
     *
     * @throws BackButtonException
     * @apiNote Exceptions tratadas nesse m�todo: InputMismatchException,
     * ConfirmException
     */
    public static boolean editarValorEstipulado(ProductService service, Product p) throws BackButtonException {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        double valorEstipulado = 0;
        try {
            System.out.println("Pressione -1 para cancelar");
            System.out.print("Novo valor estipulado: R$");
            valorEstipulado = scan.nextDouble();
            if (valorEstipulado == (int) -1) {
                ButtonUtil.botaoVoltar(0);
            }
            ButtonUtil.confirmar("alterar o valor estipulado do produto " + p.getNome() + " para " + currencyFormatter.format(valorEstipulado));
            service.editarPrecoEstipulado(p, valorEstipulado);
            return true;
        } catch (InputMismatchException e) {
            System.out.println("Digite um valor v�lido.");
        } catch (ConfirmException e) {
            System.out.println("Valor n�o alterado.");
        } catch (BackButtonException e) {
            // TODO: handle exception
        }
        return false;
    }

    /**
     * @return FALSE caso n�o deseje renomear
     *
     * @apiNote Exceptions tratadas: _ConfirmException_
     */
    public static boolean editarNomeProduto(ProductService service, Product p) {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
        System.out.print("Novo nome: ");
        String name = scan.next();
        try {
            ButtonUtil.confirmar("renomear o produto " + p.getNome() + " para " + name);
            service.editarNome(p, name);
            return true;
        } catch (ConfirmException e) {
            System.out.println("Produto " + p.getNome() + " n�o renomeado");
        }
        return false;
    }

    /***
     * @apiNote Exceptions tratadas: CategoriaException, ConfirmException,
     *          NumberFormatException
     * @return TRUE se tudo ocorrer bem, FALSE se der algo errado
     */
    public static boolean editarCategoria(Product p) {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        CategoriaService cService = new CategoriaService(p.getCategoria().getUser());
        System.out.println("Mudar o produto " + p.getNome() + " para qual categoria?\nSelecione: ");
        try {
            cService.ListarCategorias();
            String num = scan.next();
            Categoria newCat = cService.getCategoriaByNumber(Integer.parseInt(num));
            ButtonUtil.confirmar("mover o produto para a categoria " + newCat.getName());
            p.setCategoria(newCat);
            ProductService service = new ProductService(newCat);
            service.mudarCategoria(p, newCat);
            return true;
        } catch (CategoriaException e) {
            System.out.println(e.getMessage());
        } catch (ConfirmException e) {
            System.out.println("Produto n�o alterado.");
        } catch (NumberFormatException e) {
            System.out.println("Op��o inv�lida, n�o alterado.");
        }
        return false;
    }

    // -------------------- M�TODOS FUN��ES �TEIS ----------------------//

    /**
     * @apiNote Exceptions tratadas nesse m�todo: NullPointerException,
     * ListaVaziaException
     */
    public static void quantidadeGasta(ProductService service, Categoria cat) {
        try {
            double orcamento = cat.getOrcamento();
            double valorGasto = service.getValorRealGasto();
            String complemento = ". ";
            if (orcamento == 0) {
                complemento = " E voc� n�o tem um orcamento para essa lista";
            } else {
                if (valorGasto > orcamento) {
                    complemento += "Voc� j� ultrapassou seu or�amento em R$" + (valorGasto - orcamento);
                } else {
                    complemento += "Voc� ainda tem " + currencyFormatter.format((orcamento - valorGasto)) + " para gastar de acordo " +
                            "com seu or�amento de " + currencyFormatter.format(orcamento);
                }
            }
            System.out.println("Voc� j� gastou " + currencyFormatter.format(valorGasto) + complemento);
        } catch (NullPointerException e) {
            System.out.println("Voc� n�o tem or�amento para esta lista. Adicione um no menu principal");
        } catch (ListaVaziaException e) {
            System.out.println("Nenhum produto comprado at� o momento, sendo assim, voc� n�o gastou nada.");
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: NullPointerException,
     * ListaVaziaException
     */
    public static void disponivelParaComprar(ProductService service, Categoria cat) {
        try {
            double disponivel = service.getValorDisponivelParaCompra(cat);
            String complemento = "";
            if (cat.getOrcamento() == 0.0 || cat.getOrcamento() == null) {
                throw new NullPointerException();
            }
            if (disponivel < 0) {
                complemento = "Ixi! Voc� passou do seu orcamento em " + currencyFormatter.format((-(disponivel)));
                complemento += ". Voc� n�o tem mais nada dispon�vel para gastar";
                complemento += ". Or�amento para lista " + cat.getName() + ": " + currencyFormatter.format(cat.getOrcamento());
            } else {
                complemento = "Voc� tem dispon�vel " + currencyFormatter.format(disponivel) + ", de acordo com seu or�amento para " +
                        "lista " + cat.getName();
            }
            System.out.println(complemento);
        } catch (ListaVaziaException e) {
            if (cat.getOrcamento() == 0.0 || cat.getOrcamento() == null) {
                System.out.println("Voc� n�o tem or�amento para esta lista, portanto, imposs�vel saber quanto ainda tem dispon�vel " +
                        "para compra :( . Adicione um or�amento no menu principal");
            } else
                System.out.println("Voc� ainda n�o comprou nenhum produto da lista, portanto, ainda tem " + currencyFormatter.format(cat.getOrcamento()) + " para gastar");
        } catch (NullPointerException e) {
            System.out.println("Voc� n�o tem or�amento para esta lista, portanto, imposs�vel saber quanto ainda tem dispon�vel para " +
                    "compra :( . Adicione um or�amento no menu principal");
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: ListaVaziaException
     */
    public static void valorEconomizado(ProductService service) {
        try {
            double valorEconomizado = service.getValorEconomizado();
            if (valorEconomizado < 0) {
                System.out.println("Eita! Voc� n�o economizou nada! Voc� gastou " + currencyFormatter.format((-(valorEconomizado))) + " a mais do que planejava");
            } else {
                System.out.println("Voc� economizou " + currencyFormatter.format(valorEconomizado) + " Parab�ns!");
            }
        } catch (ListaVaziaException e) {
            System.out.println("Hmm, parece que voc� ainda n�o comprou nenhum produto da lista, portanto, imposs�vel saber valor " +
                    "economizado :(");
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: ListaVaziaException
     */
    public static void listarNaoConcluidos(ProductService service) {
        try {
            service.listarNaoConcluidos();
        } catch (ListaVaziaException e) {
            System.out.println("Todos os produtos da lista foram comprados :)");
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: ListaVaziaException
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
     * @return produto escolhido
     *
     * @throws Observa��o TODAS AS EXCEPTIONS J� POSSUIEM MENSAGEM,
     * APENAS USE O e.getMessage();
     * @throws NumberFormatException caso digite uma letra em vez de n�mero
     * @throws ProductoException caso n�o exista nenhum produto com n�mero
     * escolhido
     * @throws ListaVaziaException
     * @throws BackButtonException
     * @apiNote Exceptions tratadas nesse m�todo: Nenhum
     */
    public static Product selecionarProduto(ProductService service, String acao) throws NumberFormatException, ProductoException,
            ListaVaziaException, BackButtonException {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        service.listarPordutos();
        System.out.println("Pressione 0 para cancelar");
        System.out.print("Esolha qual produto deseja " + acao + ": ");
        String produtoEscolhido = scan.next();
        ButtonUtil.botaoVoltar(produtoEscolhido);
        Product p = service.getProdutoByNumer(Integer.parseInt(produtoEscolhido));
        return p;
    }

    // ------------------------- m�todos privados -------------------------------//

    /**
     * @apiNote Exceptions tratadas nesse m�todo: InputMismatchException
     */
    private static Double adicionarEditarValorEstiupulado(Scanner scan) throws EntradaInvalidaException {
        System.out.print("Qual o pre�o que voc� acha que vai pagar? R$");
        try {
            return scan.nextDouble();
        } catch (InputMismatchException e) {
            throw new EntradaInvalidaException("Digite um valor v�lido");
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: InputMismatchException
     */
    private static Double adicionarEditarValorReal(Scanner scan) throws EntradaInvalidaException {
        System.out.println("OBS: se voc� n�o comprou o produto ainda, deixe 0");
        System.out.print("Qual o pre�o que voc� realmente pagou? R$");
        try {
            return scan.nextDouble();
        } catch (InputMismatchException e) {
            throw new EntradaInvalidaException("Valor inv�lido");
        }
    }

    /**
     * @param valorReal - valor real podendo ser qualquer, mas caso seja 0, deixa
     * null eliminar verbose apenas. se o valor real for
     * "diferente" de 0, entao ele marca como concluido, senao
     * deixa null { service.marcarComoConcluido(p, valorReal);
     * @param service - ProductService service
     * @param p - Product p
     *
     * @apiNote Exceptions tratadas nesse m�todo: Nenhuma
     */
    private static void concluir(Double valorReal, ProductService service, Product p) {
        if (!(valorReal == 0)) {
            service.marcarComoConcluido(p, valorReal);
        } else {
            valorReal = null;
        }
    }

    /**
     * @param funcao caso a fun��o seja verdadeira, pergunta se quer alterar o
     * valorReal
     *
     * @return TRUE CASO QUEIRA ALTERAR O VALOR OU ESCOLHA QUE N�O, FALSE SE ALGO
     * DER ERRADO
     *
     * @throws NumberFormatException
     * @throws BackButtonException
     * @apiNote Exceptions tratadas nesse m�todo: Nenhuma pergunta se quer alterar o
     * Valor caso a fun��o seja verdadeira
     */
    private static boolean alterarValor(boolean funcao, Scanner scan, Product p, ProductService service) throws NumberFormatException, BackButtonException {
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
     * @apiNote Exceptions tratadas nesse m�todo: nenhuma
     */
    private static String perguntarAlterarValorReal(Scanner scan, Product p) {
        System.out.println("Pressione 0 para cancelar");
        System.out.println("O pre�o real atual � de " + currencyFormatter.format(p.getPrecoReal()) + ". Deseja alterar esse valor?");
        System.out.println("[ 1 ] - sim");
        System.out.println("[ 2 ] - nop");
        return scan.next();
    }

    public static void mostrarSomaTotal(ProductService service) {
        System.out.println("---------------------------------------");
        System.out.println("Valor Total Estipulado: " + currencyFormatter.format(service.getValorGastoEstipulado()));
        System.out.println("Valor Total: " + currencyFormatter.format(service.getValorTotalAtual()));
    }

    public static void mostrarInfosProdutos(User user, ProductService service, double orcamento) {
        int qntProdutos = 0, qntProdutosComprados = 0;
        double valorRealGasto = 0, valorEstipulado = service.getValorGastoEstipulado();
        try {
            qntProdutos = service.findAllProduct().size();
        } catch (ListaVaziaException e) {
            qntProdutos = 0;
        }
        try {
            qntProdutosComprados = service.getProdutosConcluidos().size();
        } catch (ListaVaziaException e) {
            qntProdutosComprados = 0;
        }
        try {
            valorRealGasto = service.getValorRealGasto();
        } catch (ListaVaziaException e) {
            valorRealGasto = 0;
        }

        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        System.out.println("Informa��es breves: ");
        System.out.println("Voc� possui " + qntProdutos + " produtos na lista atual");
        System.out.println("Voc� j� comprou " + qntProdutosComprados + " produtos de um total de " + qntProdutos);
        System.out.println("Voc� j� gastou " + currencyFormatter.format(valorRealGasto));
        System.out.println("Falta gastar " + currencyFormatter.format(service.getValorEstipuladoRestante()));
        System.out.println("O valor estipulado atual � de " + currencyFormatter.format(valorEstipulado));
        System.out.println("O valor total atual � de " + currencyFormatter.format(service.getValorTotalAtual()));
        System.out.println("Or�amento: " + currencyFormatter.format(orcamento));
    }
}
