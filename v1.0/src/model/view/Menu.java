package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scan = new Scanner(System.in);

    public static void menuPrincipalCategorias() {
        System.out.println("[ 1 ] - Acessar suas Listas");
        System.out.println("[ 2 ] - Adicionar nova Lista");
        System.out.println("[ 3 ] - Editar Lista");
        System.out.println("[ 4 ] - Excluir Lista");
        System.out.println("[ 5 ] - Configura��es de Conta");
        System.out.println("[ 0 ] - Sair");
    }

    public static void menuPrincipalProdutos() {
        System.out.println(String.format("%s",
                "----------------------------------------------------------------------------------------------------------------"));
        System.out.println("[ 1 ] - Acessar fun��es �teis");
        System.out.println("[ 2 ] - Adicionar Novo Produto");
        System.out.println("[ 3 ] - Editar Produto");
        System.out.println("[ 4 ] - Excluir Produto");
        System.out.println("[ 5 ] - Adicionar/Editar Or�amento");
        System.out.println("[ 0 ] - Voltar");
    }

    public static void menuEditarProduto() {
        System.out.println("[ 1 ] - Editar tudo");
        System.out.println("[ 2 ] - Editar nome");
        System.out.println("[ 3 ] - Editar Pre�o Estipulado");
        System.out.println("[ 4 ] - Editar Pre�o Real");
        System.out.println("[ 5 ] - Marcar como comprado");
        System.out.println("[ 6 ] - Marcar como n�o comprado");
        System.out.println("[ 7 ] - Mudar categoria");
        System.out.println("[ 0 ] - Voltar");
    }

    public static void menuEditarCategorias() {
        System.out.println("[ 1 ] - Editar Tudo");
        System.out.println("[ 2 ] - Editar Nome");
        System.out.println("[ 3 ] - Editar Or�amento");
        System.out.println("[ 0 ] - Voltar");
    }

    public static boolean continuarEditando() throws InputMismatchException {
        System.out.println("Feito!");
        System.out.println("Quer continuar editando?");
        System.out.println("[ 1 ] - sim");
        System.out.println("[ 2 ] - n�o");
        int n = scan.nextInt();
        return n == 1;
    }

    public static void menuFinanceiro() {
        System.out.println(String.format("%s",
                "----------------------------------------------------------------------------------------------------------------"));
        System.out.println("[ 1 ] - Quanto voc� j� gastou");
        System.out.println("[ 2 ] - Listar Produtos j� comprados");
        System.out.println("[ 3 ] - Listar Produtos que faltam comprar");
        System.out.println("[ 4 ] - Quanto dispon�vel voc� ainda tem para gastar");
        System.out.println("[ 5 ] - Quanto voc� economizou");
        System.out.println("[ 0 ] - Voltar");
    }

    public static void menuConfiguracoesConta() {
        System.out.println("[ 1 ] - Alterar nome");
        System.out.println("[ 2 ] - Alterar username");
        System.out.println("[ 3 ] - Alterar senha");
        System.out.println("[ 0 ] - Voltar");
    }

}
