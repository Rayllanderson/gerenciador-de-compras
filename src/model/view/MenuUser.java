package model.view;

import java.util.Scanner;

import model.entities.User;
import model.exception.BackButtonException;
import model.exception.OpcaoInvalidaException;
import model.service.UserService;
import model.util.UserUtil;

public class MenuUser {

    public static boolean opcoesAccount(User user) {
	UserService service = new UserService();
	String opcao = null;
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter(System.lineSeparator());
	while (true) {
	    System.out.println("Atual username: " + user.getUsername());
	    try {
		System.out.println("Escolha o que deseja alterar: ");
		Menu.menuConfiguracoesConta();
		opcao = scan.next();
		switch (Integer.parseInt(opcao)) {
		case 0:
		    return false;
		case 1:
		    return encurtarVerbose(UserUtil.alterarNome(user, service, scan), "nome");
		case 2:
		    return encurtarVerbose(UserUtil.alterarUsername(user, service, scan), "username");
		case 3:
		    return encurtarVerbose(UserUtil.alterarSenha(user, service, scan), "senha");
		default:
		    throw new OpcaoInvalidaException("Opção inválida");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Entrada inválida! Digite apenas números");
	    } catch (BackButtonException e) {
		return false;
	    } catch (OpcaoInvalidaException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    private static boolean encurtarVerbose(boolean funcao, String alteracao) {
	if (alteracao.equalsIgnoreCase("senha"))
	    alteracao = "senha alterada";
	if (funcao) {
	    System.out.println(alteracao + " alterado com sucesso!");
	    return true;
	}
	return false;
    }

}
