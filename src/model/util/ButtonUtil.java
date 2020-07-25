package model.util;

import java.util.Scanner;

import model.exception.BackButtonException;

public class ButtonUtil {
    
    private static Scanner scan = new Scanner (System.in);
    
    public static void botaoVoltar(Object opcao) {
	if (opcao.equals("0") || opcao.equals(0)) {
	    throw new BackButtonException();
	}
    }
    
    public static boolean confirmar (String acao) throws NumberFormatException {
	String choose = null;
	System.out.println("Você tem certeza que deseja " + acao + "?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - não");
	choose = scan.next();
	return Integer.parseInt(choose) == 1;
    }
}
