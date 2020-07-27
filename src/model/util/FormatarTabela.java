package model.util;

import java.text.NumberFormat;
import java.util.List;

import model.entities.Product;

public class FormatarTabela {
    private static NumberFormat formatar = NumberFormat.getCurrencyInstance();
    public static void printInvoiceHeader(int maxLenName, int maxLenPrice) {
	System.err.println(String.format(//677764
		"%3s %3s %-" + maxLenName + "s " + "%6s %4s %5s %5s %4s %4s",
		"ID", "|", "Nome", "|", "  Preço Estipulado(R$)", "|", "  Preço Real(R$)", "|", "  Comprado"));
	System.out.println(String.format("%s",
		"----------------------------------------------------------------------------------------------------------------"));
    }

    public static void printInvoice(Product p, int maxLenName, int maxLenPrice, int id) {
	System.out.println(String.format(
		"%3s %3s %-" + maxLenName + "s " + "%" + maxLenPrice + "s %22s " + "%" + maxLenPrice + "s %12s %8s %8s",
		id, "|", p.getNome(), "|", formatar.format(p.getPrecoEstipulado()), "|", formatar.format(p.getPrecoReal()), "|", p.comprado()));
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
