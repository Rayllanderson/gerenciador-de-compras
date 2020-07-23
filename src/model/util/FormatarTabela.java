package model.util;

import java.util.List;

import model.entities.Product;

public class FormatarTabela {

    public static void printInvoiceHeader(int maxLenName, int maxLenPrice) {
	System.err.println(String.format(
		"%3s %3s %-" + maxLenName + "s " + "%" + maxLenPrice + "s %10s " + "%" + maxLenPrice + "s %10s %4s %4s",
		"ID", "|", "Nome", "|", "Preço Estipulado(R$)", "|", "Preço Real(R$)", "|", "  Comprado"));
	System.out.println(String.format("%s",
		"----------------------------------------------------------------------------------------------------------------"));
    }

    public static void printInvoice(Product p, int maxLenName, int maxLenPrice, int id) {
	System.out.println(String.format(
		"%3s %3s %-" + maxLenName + "s " + "%" + maxLenPrice + "s %20s " + "%" + maxLenPrice + "s %10s %8s %8s",
		id, "|", p.getNome(), "|", p.getPrecoEstipulado(), "|", p.getPrecoReal(), "|", p.comprado()));
    }

    public static int maxLenghtName(List<Product> list) {
	int maxLen = list.get(0).getNome().length();

	for (int i = 1; i < list.size(); i++) {
	    if (list.get(i).getNome().length() > maxLen) {
		maxLen = list.get(i).getNome().length();
	    }
	}
	return maxLen;
    }
}
