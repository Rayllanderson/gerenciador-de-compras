package com.ray.model.util;

import java.util.List;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.ProductService;
import com.ray.model.service.ProductServiceConsole;

public class ProdutosUtil {

    private ProductService service;
    
    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void listarConcluidos(ProductServiceConsole service) {
	try {
	    service.listarConcluidosConsole();
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }

    /**
     * 
     * @return lista contendo os produtos marcados como não comprados
     * @throws ListaVaziaException caso não possua nenhum produto na lista ou caso
        *  nenhum produto da lista ainda não tenha sido comprado <br>
     */
    public List<Product> getNaoConcluidos() throws ListaVaziaException {
   	List<Product> list = service.findAll();
   	if (list.isEmpty()) {
   	    throw new ListaVaziaException("Você não tem produtos na lista");
   	}
   	for (int i = 0; i < list.size(); i++) {
   	    if (list.get(i).isComprado()) {
   		list.remove(i);
   		i--;
   	    }
   	}
   	if (list.isEmpty()) {
   	    throw new ListaVaziaException("Todos os produtos da lista foram comprados :)");
   	}
   	return list;
       }

       /** 
        *  @return uma lista de todos os produtos que foram concluídos
        *  @throws ListaVaziaException caso não possua nenhum produto na lista ou caso
        *  nenhum produto da lista tenha sido comprado <br>
        *  ListaVaziaException("Você não tem produtos na
        *  lista"); <br>
        *  ListaVaziaException("Puxa, nenhum produto foi
        *  comprado até o momento :(");
        */
       public List<Product> getConcluidos() throws ListaVaziaException {
   	List<Product> list = service.findAll();
   	if (list.isEmpty()) {
   	    throw new ListaVaziaException("Você não tem produtos na lista");
   	}
   	for (int i = 0; i < list.size(); i++) {
   	    if (!(list.get(i).isComprado())) {
   		list.remove(i);
   		i--;
   	    }
   	}
   	if (list.isEmpty()) {
   	    throw new ListaVaziaException("Puxa, nenhum produto foi comprado até o momento :(");
   	}
   	return list;
       }

      
   
    // -----------------------------SOMAS--------------------------------------//
       
    /**
     * 
     * @return soma total dos preços reais de todos os produtos concluídos
     * @throws ListaVaziaException caso não tenha nenhum produto na lista
     */
    public double getTotalGasto() throws ListaVaziaException {
	double sum = 0;
	List<Product> list = this.getConcluidos();
	for (Product p : list) {
	    sum += p.getPrecoReal();
	}
	return sum;
    }

    /**
     * 
     * @return valor estipulado total de todos os produtos
     * @throws ListaVaziaException caso não tenha nenhum produto na lista
     */
    public double getTotalEstipulado() throws ListaVaziaException {
	double sum = 0;
	List<Product> list = service.findAll();
	for (Product p : list) {
	    sum += p.getPrecoEstipulado();
	}
	return sum;
    }

    /**
     * 
     * @return valor total. Soma dos produtos comprados. Se um produto não tiver
     *         sido comprado, considera o valor estipulado<br>
     *         valorReal + valorEstipulado (if !comprado)
     */
    public double getTotalAtual() {
	double sum = 0;
	List<Product> list = service.findAll();
	for (Product p : list) {
	    if (p.getPrecoReal() != 0 && !p.isComprado()) {
		sum += p.getPrecoReal();
	    } else {
		sum += p.getPrecoEstipulado();
	    }
	}
	return sum;
    }

    /**
     * 
     * @param service
     * @return a soma total do valor estipulado para os produtos que não foram
     *         comprados Se todos os produtos forem comprados, retorna 0
     */
    public double getEstipuladoRestante() {
	try {
	    List<Product> list = getNaoConcluidos();
	    return list.stream().mapToDouble(Product::getPrecoEstipulado).sum();
	} catch (ListaVaziaException e) {
	    return 0.0;
	}
    }
    
    /**
     * 
     * @return PrecoEstipulado - PrecoReal;
     * @throws ListaVaziaException caso não tenha nenhum produto comprado
     */
    public double getValorEconomizado() throws ListaVaziaException {
	double total = 0;
	List<Product> list = this.getConcluidos();
	for (Product p : list) {
	    total += p.getPrecoEstipulado() - p.getPrecoReal();
	}
	return total;
    }

    /**
     * 
     * @param cat
     * @return total = orcamento - this.getValorRealGasto();
     * @throws NullPointerException
     * @throws ListaVaziaException  quando não há produtos na lista
     */
    public double getDisponivel(Categoria cat) throws NullPointerException, ListaVaziaException {
	try {
	    return cat.getOrcamento() - this.getTotalGasto();
	} catch (ListaVaziaException e) {
	    return 0.0;
	}
    }
}
