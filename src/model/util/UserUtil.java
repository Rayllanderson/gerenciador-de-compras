package model.util;

import java.util.Scanner;

public class UserUtil {
    
    public static String formatarNome(String nome) {
	String[] name = nome.split(" ");
	return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }

    /**
     * {@code}
     * System.out.print(pedido + ": ");
     * <br><br>return scan.next();
     */
    public static String pedirUserOuSenha(Scanner scan, String pedido) {
	System.out.print(pedido + ": ");
	return scan.next().trim();
    }


}
