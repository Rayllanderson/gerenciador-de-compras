package com.ray.model.util;

import java.util.Scanner;

import com.ray.model.exception.BackButtonException;
import com.ray.model.exception.ConfirmException;

public class ButtonUtil {
    
    private static Scanner scan = new Scanner (System.in);
    
    /**
     * 
     * @param opcao
     * @throws BackButtonException
     */
    public static void botaoVoltar(Object opcao) throws BackButtonException{
	if (opcao.equals("0") || opcao.equals(0)) {
	    throw new BackButtonException();
	}
    }
    
    /**
     * {@code}System.out.println("Você tem certeza que deseja " + acao + "?");
     * @param ação: o que você deseja + acao. ex: acao = excluir; sendo assim: o que você deseja excluir?
     * @return true caso confirme a ação
     * @throws NumberFormatException caso digite uma letra em vez de número
     * @throws ConfirmException caso o valor digitado seja 2 ou diferente de 1
     */
    public static boolean confirmar (String acao) throws NumberFormatException, ConfirmException {
	String choose = null;
	System.out.println("Você tem certeza que deseja " + acao + "?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - não");
	choose = scan.next();
	if (Integer.parseInt(choose) == 1) {
	    return true;
	}else
	    throw new ConfirmException();
    }
}
