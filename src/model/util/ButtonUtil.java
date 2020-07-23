package model.util;

import model.exception.BackButtonException;

public class ButtonUtil {
    
    public static void botaoVoltar(Object opcao) {
	if (opcao.equals("0") || opcao.equals(0)) {
	    throw new BackButtonException();
	}
    }
}
