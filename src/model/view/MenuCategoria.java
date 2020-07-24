package model.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Categoria;
import model.service.CategoriaService;
import model.util.ButtonUtil;
import model.util.CategoriaUtil;

public class MenuCategoria {
    private static Scanner scan = new Scanner(System.in);
    
    public static void editarCategoria(CategoriaService service) {
	scan.useDelimiter(System.lineSeparator());
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
	    CategoriaUtil.editarCategoria(service, "tudo", cat);
	    break;
	case 2:
	    CategoriaUtil.editarCategoria(service, "nome", cat);
	    break;
	case 3:
	    CategoriaUtil.editarCategoria(service, "orcamento", cat);
	    break;
	default:
	    throw new InputMismatchException();
	}
    }


}
