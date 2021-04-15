package model.util;

import java.util.Scanner;

import model.exception.BackButtonException;
import model.exception.ConfirmException;

public class ButtonUtil {

    private static Scanner scan = new Scanner(System.in);

    /**
     * @param opcao
     *
     * @throws BackButtonException
     */
    public static void botaoVoltar(Object opcao) throws BackButtonException {
        if (opcao.equals("0") || opcao.equals(0)) {
            throw new BackButtonException();
        }
    }

    /**
     * {@code}System.out.println("Voc� tem certeza que deseja " + acao + "?");
     *
     * @param a��o: o que voc� deseja + acao. ex: acao = excluir; sendo assim: o que voc� deseja excluir?
     *
     * @return true caso confirme a a��o
     *
     * @throws NumberFormatException caso digite uma letra em vez de n�mero
     * @throws ConfirmException caso o valor digitado seja 2 ou diferente de 1
     */
    public static boolean confirmar(String acao) throws NumberFormatException, ConfirmException {
        String choose = null;
        System.out.println("Voc� tem certeza que deseja " + acao + "?");
        System.out.println("[ 1 ] - sim");
        System.out.println("[ 2 ] - n�o");
        choose = scan.next();
        if (Integer.parseInt(choose) == 1) {
            return true;
        } else
            throw new ConfirmException();
    }
}
