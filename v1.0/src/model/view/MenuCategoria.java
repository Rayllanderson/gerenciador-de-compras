package model.view;

import java.util.Scanner;

import model.entities.Categoria;
import model.exception.BackButtonException;
import model.exception.OpcaoInvalidaException;
import model.service.CategoriaService;
import model.service.ProductService;
import model.util.CategoriaUtil;
import model.util.ProdutosUtil;

public class MenuCategoria {

    public static void editarCategoria(CategoriaService service) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
        try {
            System.out.println("Selecione a lista que deseja Editar: ");
            Categoria cat = CategoriaUtil.selecionarCategoria(service, scan);
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
                    throw new OpcaoInvalidaException("N�o existe essa op��o no momento");
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite uma op��o v�lida");
        } catch (BackButtonException e) {
        } catch (OpcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return TRUE se nao quiser adicionar um produto, False se quiser adicionar
     *
     * @apiNote Exceptions tratadas nesse m�todo: NumberFormatException(af, nem precisa, mas whatever) BackButtonException
     */
    public static boolean createNewList(ProductService service, Categoria cat) {
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);
        System.out.println("Opa! Parece que sua lista est� vazia :(");
        System.out.println("Deseja adicionar um novo produto a essa lista?");
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
        }
        return false;
    }

}
