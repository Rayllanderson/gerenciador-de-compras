package model.view;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.Categoria;
import model.entities.User;
import model.exception.BackButtonException;
import model.exception.ListaVaziaException;
import model.exception.MyLoginException;
import model.exception.OpcaoInvalidaException;
import model.service.CategoriaService;
import model.service.ProductService;
import model.util.CategoriaUtil;
import model.util.ProdutosUtil;
import model.util.UserUtil;

public class TelaPrincipal {

    private static Scanner scan = new Scanner(System.in);
    private UserDao telaLogin;

    public TelaPrincipal() {
        this.telaLogin = DaoFactory.createTelaLoginDao();
        scan.useDelimiter(System.lineSeparator());
    }

    // --------------------------- MENU LOGIN ----------------------------------
    public User logar() {
        String username = null, password = null;
        while (true) {
            try {
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("|  [ 1 ] - Login      |");
                System.out.println("|  [ 2 ] - Cadastrar  |");
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                String op = scan.next().trim();
                if (Integer.parseInt(op) == 1) {
                    username = UserUtil.pedirAlgo(scan, "Usu�rio");
                    password = UserUtil.pedirAlgo(scan, "Senha");
                    return telaLogin.login(username, password);
                } else if (Integer.parseInt(op) == 2) {
                    System.out.print("Nome: ");
                    String name = scan.next();
                    username = UserUtil.pedirAlgo(scan, "Usu�rio");
                    password = UserUtil.pedirAlgo(scan, "Senha");
                    if (this.telaLogin.cadastrar(new User(null, name, username, password))) {
                        System.out.println("Cadastro realizado com sucesso! Fa�a login para continuar");
                    }
                } else {
                    throw new OpcaoInvalidaException("Digite uma op��o v�lida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opcao v�lida");
            } catch (MyLoginException e) {
                System.out.println(e.getMessage());
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Ocorreu um erro inesperado");
            }
        }
    }

    // -------------------- MENUS CATEGORIA -----------------------------
    @SuppressWarnings("resource")
    public Categoria telaCategoria(User user) {
        CategoriaService service = new CategoriaService(user);
        do {
            Scanner scan = new Scanner(System.in);
            String opcao = null;
            System.out.println("O que deseja fazer, " + UserUtil.formatarNome(user.getName()) + "?");
            Menu.menuPrincipalCategorias();
            try {
                opcao = scan.next();
                switch (Integer.parseInt(opcao)) {
                    case 0:
                        scan.close();
                        System.exit(0);
                    case 1:
                        return CategoriaUtil.selecionarCategoria(service, scan);
                    case 2:
                        CategoriaUtil.adicionarCategoria(service, user);
                        break;
                    case 3:
                        MenuCategoria.editarCategoria(service);
                        break;
                    case 4:
                        CategoriaUtil.deletarCategoria(service);
                        break;
                    case 5:
                        UserUtil.mostrarInfos(user);
                        MenuUser.opcoesAccount(user);
                        break;
                    default:
                        throw new OpcaoInvalidaException("Op��o inv�lida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Op��o inexistente no momento");
            } catch (BackButtonException e) {
            } catch (NumberFormatException e) {
                System.out.println("Op��o inv�lida. Tente digitar apenas n�meros");
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Ocorreu um erro inesperado");
            }
        } while (true);
    }

    // --------------------------- MENUS PRODUTO ----------------------------------
    public boolean telaProduto(Categoria cat) {
        ProductService service = new ProductService(cat);
        String opcao = null;
        while (true) {
            try {
                @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
                ProdutosUtil.mostrarSomaTotal(service);
                mostrarOrcamento(cat);
                service.listarPordutos();
                Menu.menuPrincipalProdutos();
                opcao = scan.next();
                switch (Integer.parseInt(opcao)) {
                    case 0:
                        return true;
                    case 1:
                        while (MenuProduto.funcoesUteis(service, cat))
                            ;
                        break;
                    case 2:
                        ProdutosUtil.adicionarProduto(service, cat);
                        break;
                    case 3:
                        while (MenuProduto.menuEditarProduto(service))
                            ;
                        break;
                    case 4:
                        ProdutosUtil.deletarProduto(service);
                        break;
                    case 5:
                        CategoriaUtil.adicionarOrcamento(new CategoriaService(cat.getUser()), cat);
                        break;
                    default:
                        throw new OpcaoInvalidaException("Op��o inv�lida! Tente novamente.");
                }
            } catch (BackButtonException e) {
            } catch (NumberFormatException e) {
                System.out.println("Tente digitar apenas n�meros");
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (ListaVaziaException e) {
                return MenuCategoria.createNewList(service, cat);
            } catch (RuntimeException e) {
                System.out.println("Ocorreu um erro inesperado");
            }
        }
    }

    // ------------------------- M�TODOS B�SICOS --------------------------------

    private void mostrarOrcamento(Categoria cat) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String complemento = ": ";
        if (cat.getOrcamento() == 0 || cat.getOrcamento() == null) {
            complemento += "Lista sem or�amento";
        } else {
            complemento += currencyFormatter.format(cat.getOrcamento());
        }
        System.out.println("Or�amento para esta Lista (" + cat.getName() + ")" + complemento);
        System.out.println();
    }

    // TODO:FU�AR OS BUGS RESTANTES
}
