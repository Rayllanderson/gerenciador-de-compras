package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.exception.BackButtonException;
import model.exception.CategoriaException;
import model.service.CategoriaService;
import model.service.ProductService;
import model.util.ButtonUtil;
import model.util.CategoriaUtil;
import model.util.ProdutosUtil;

public class MenuCategoria {
    private static Scanner scan = new Scanner(System.in);

    public static void editarCategoria(CategoriaService service) {
	scan.useDelimiter(System.lineSeparator());
	try {
	    System.out.println("Selecione a lista que deseja Editar: ");
	    System.out.println("Pressione 0 para voltar");
	    service.ListarCategorias();
	    String num = scan.next();
	    ButtonUtil.botaoVoltar(num);
	    Categoria cat = service.getCategoriaByNumber(Integer.parseInt(num));
	    Menu.menuEditarCategorias();
	    String choose = scan.next();
	    switch (Integer.parseInt(choose)) {
	    case 0:
		break;
	    case 1:
		CategoriaUtil.editarTudoCategoria(service, "tudo", cat);
		break;
	    case 2:
		CategoriaUtil.editarTudoCategoria(service, "nome", cat);
		break;
	    case 3:
		CategoriaUtil.editarTudoCategoria(service, "orcamento", cat);
		break;
	    default:
		throw new InputMismatchException();
	    }
	} catch (CategoriaException e) {
	    System.out.println(e.getMessage());
	}catch (NumberFormatException e) {
	    System.out.println("Digite uma op��o v�lida");
	}catch (InputMismatchException e) {
	   System.out.println(e.getMessage());
	}
    }

    public static boolean createNewList(ProductService service, Categoria cat) {
	System.out.println("Adicionar novo produto a essa lista?");
	System.out.println("[ 1 ] - sim");
	System.out.println("[ 2 ] - n�o");
	String n = null;
	try {
	    n = scan.next();
	    if (Integer.parseInt(n) == 1) {
		ProdutosUtil.adicionarProduto(service, cat);
	    } else {
		return true;
	    }
	} catch (NumberFormatException e) {
	    System.out.println("digite apenas n�meros");
	} catch (BackButtonException e) {
	    return true;
	}catch (InputMismatchException e) {
	    System.out.println("Digite um valor v�lido");
	}
	return false;
    }
}