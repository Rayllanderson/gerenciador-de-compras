package com.ray.model.util;

import java.util.List;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.ProductServiceConsole;

public class ProdutosUtilConsole extends ProdutosUtil{
    
    public ProdutosUtilConsole(Categoria cat) {
	super(cat);
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public void listarConcluidos(ProductServiceConsole service) {
	try {
	    listarConcluidosConsole();
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }
    
    /**
     * @throws ListaVaziaException("Ops, parece que você não tem nenhum produto na
     *                                   lista.");
     */
    public void listarPordutosConsole() throws ListaVaziaException {
	List<Product> list = getList();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 6);
	for (int i = 0; i < list.size(); i++) {
	    FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	}
    }

    public void listarNaoConcluidosConsole() throws ListaVaziaException {
	List<Product> list = getNaoConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (!(list.get(i).isComprado())) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }

    public void listarConcluidosConsole() throws ListaVaziaException {
	List<Product> list = getConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i).isComprado()) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }
    
}
