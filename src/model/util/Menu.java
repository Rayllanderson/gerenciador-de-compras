package model.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scan = new Scanner(System.in);
    
    public static void menuCategorias() throws InputMismatchException{
	System.out.println("[ 1 ] - Acessar suas Listas");
	System.out.println("[ 2 ] - Adicionar nova Lista");
	System.out.println("[ 3 ] - Editar Lista");
	System.out.println("[ 4 ] - Excluir Lista");
	System.out.println("[ 0 ] - Sair");
    }

    public static void menuProdutos() throws InputMismatchException{
	System.out.println(String.format("%s",
		"----------------------------------------------------------------------------------------------------------------"));
	System.out.println("[ 1 ] - Adicionar Novo Produto");
	System.out.println("[ 2 ] - Editar Produto");
	System.out.println("[ 3 ] - Excluir Produto");
	System.out.println("[ 0 ] - Voltar");
    }

    public static void menuEditarProduto() throws InputMismatchException{
	System.out.println("[ 1 ] - Editar tudo");
	System.out.println("[ 2 ] - Editar nome");
	System.out.println("[ 3 ] - Editar Preço Estipulado");
	System.out.println("[ 4 ] - Editar Preço Real");
	System.out.println("[ 5 ] - Marcar como comprado");
	System.out.println("[ 6 ] - Marcar como não comprado");
	System.out.println("[ 0 ] - Voltar");
    }

    public static boolean naoContinuarEditando() throws InputMismatchException {
	System.out.println("Feito!");
	System.out.println("Quer continuar editando?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - não");
	int n = scan.nextInt();
	return n == 2;
    }

}
