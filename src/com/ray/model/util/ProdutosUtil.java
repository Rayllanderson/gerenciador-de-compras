package com.ray.model.util;

import java.text.NumberFormat;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.ProductService;

public class ProdutosUtil {

    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
   
    // -------------------- MÉTODOS FUNÇÕES ÚTEIS ----------------------//
    /**
     * @apiNote Exceptions tratadas nesse método: NullPointerException,
     *          ListaVaziaException
     */
    public static String quantidadeGasta(ProductService service, Categoria cat) {
	try {
	    double orcamento = cat.getOrcamento();
	    double valorGasto = service.getValorRealGasto();
	    String complemento = ". ";
	    if (orcamento == 0) {
		complemento = " E você não tem um orcamento para essa lista";
	    } else {
		if (valorGasto > orcamento) {
		    complemento += "Você já ultrapassou seu orçamento em R$" + (valorGasto - orcamento);
		} else {
		    complemento += "Você ainda tem " + currencyFormatter.format((orcamento - valorGasto))
			    + " para gastar de acordo com seu orçamento de " + currencyFormatter.format(orcamento);
		}
	    }
	    return "Você já gastou " + currencyFormatter.format(valorGasto) + complemento;
	} catch (NullPointerException e) {
	    return "Você não tem orçamento para esta lista. Adicione um no menu principal";
	} catch (ListaVaziaException e) {
	    return "Nenhum produto comprado até o momento, sendo assim, você não gastou nada.";
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: NullPointerException,
     *          ListaVaziaException
     */
    public static String disponivelParaComprar(ProductService service, Categoria cat) {
	 Double orcamento = 0.0;
	 double somaTotalNaoConcluidos = service.getValorEstipuladoRestante();
	 if(somaTotalNaoConcluidos == 0 && !service.listIsEmpty()) {
	     return "Todos os produtos foram comprados :)";
	 }
	try {
	    double disponivel = 0;
	    String complemento = "";
	    orcamento = cat.getOrcamento();
	    if (orcamento == 0.0 || orcamento == null) {
		throw new NullPointerException();
	    }
	    disponivel = orcamento - service.getValorRealGasto();
	    if (disponivel < 0) {
		complemento = "<font color=" + HtmlColors.RED +"> Ixi! Você passou do seu orcamento em " + currencyFormatter.format((-(disponivel)));
		complemento += ". Você não tem mais nada disponível para gastar";
		complemento += ". Orçamento para lista " + cat.getName() + ": "
			+ currencyFormatter.format(orcamento);
	    } else {
		if(disponivel >0) {
		complemento = "<font color=" + HtmlColors.GREEN +"> Você tem disponível " + currencyFormatter.format(disponivel)
			+ ", de acordo com seu orçamento para lista " + cat.getName();
		double valorTotalNaoConcluidos = disponivel - somaTotalNaoConcluidos;
		if(valorTotalNaoConcluidos != 0) {
		   
		    if (valorTotalNaoConcluidos < 0) {
			complemento += "<br> <font color=" + HtmlColors.RED +"> Se comprar todos os produtos da lista, passará em " + currencyFormatter.format(-(valorTotalNaoConcluidos)) + " do seu orcamento";
		    }else {
			complemento += "<br> Se comprar todos os produtos da lista, ficará com " + currencyFormatter.format(valorTotalNaoConcluidos);
		    }
		}
		}else if (disponivel == 0 && somaTotalNaoConcluidos == 0){
		complemento += "Nada! Você atingiu sua meta e comprou todos os produtos exatamente de acordo com seu orçamento! Seguiu a lista risca, Parabéns!";
		}else {
		complemento += "Você antigiu seu orçamento e a partir de agora, tudo passará de seu orcamento"
			+ "	<br>Comprando tudo, ao final, passará em <font color=" + HtmlColors.RED +">" + currencyFormatter.format(-somaTotalNaoConcluidos) + " do seu orçamento.";
		}
	    }
	    complemento+="<font color=\"black\">";
	    return complemento;
	} catch (ListaVaziaException e) {
	    if (orcamento == 0.0 || orcamento == null) {
		return "Você não tem orçamento para esta lista, portanto, impossível saber quanto ainda tem disponível para compra :( . Adicione um orçamento no menu principal";
	    } else 
		return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado até o momento :(") ? "Você ainda não comprou nenhum produto da lista. Então você ainda tem "
			+ currencyFormatter.format(orcamento) + " disponível para gastar" : e.getMessage();
	} catch (NullPointerException e) {
	    return "Você não tem orçamento para esta lista. Por isso, infelizmente, não é possível saber o quanto você ainda tem disponível. Adicione um orçamento no menu principal.";
	}
    }


    
    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static String valorEconomizado(ProductService service) {
	try {
	    double valorEconomizado = service.getValorEconomizado();
	    if (valorEconomizado < 0) {
		return "<font color=" + HtmlColors.RED +">Eita! Você não economizou nada! Você gastou " + currencyFormatter.format((-(valorEconomizado)))
			+ " a mais do que planejava <font color=\"black\">";
	    }else if(valorEconomizado == 0) {
		return "Até o momento, você está seguindo sua lista a risca! Não economizou nada e também não gastou mais do que deveria. Está indo bem!";
	    }
	    return "<font color=" + HtmlColors.GREEN +">Você economizou " + currencyFormatter.format(valorEconomizado) + " Parabéns! <font color=\"black\">";
	} catch (ListaVaziaException e) {
	    return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado até o momento :(") ? "Você ainda não comprou nenhum produto da lista. No momento, impossível saber valor economizado :(" : e.getMessage();
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void listarNaoConcluidos(ProductService service) {
	try {
	    service.listarNaoConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Todos os produtos da lista foram comprados :)");
	}
    }

    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static void listarConcluidos(ProductService service) {
	try {
	    service.listarConcluidos();
	} catch (ListaVaziaException e) {
	    System.out.println("Nenhum produto da lista foi comprado :(");
	}
    }

  
    public static void mostrarSomaTotal(ProductService service) {
	System.out.println("---------------------------------------");
	System.out.println("Valor Total Estipulado: " + currencyFormatter.format(service.getValorTotalEstipulado()));
	System.out.println("Valor Total: " + currencyFormatter.format(service.getValorTotalAtual()));
    }

    public static String mostrarInfosProdutos(User user, ProductService service, double orcamento) {
	int qntProdutos = 0, qntProdutosComprados = 0;
	double valorRealGasto = 0, valorEstipulado = service.getValorTotalEstipulado();
	double valorEstipuladoRestante = 0;
	try {
	    qntProdutos = service.findAllProduct().size();
	} catch (ListaVaziaException e) {
	    qntProdutos = 0;
	}
	try {
	    qntProdutosComprados = service.getProdutosConcluidos().size();
	} catch (ListaVaziaException e) {
	    qntProdutosComprados = 0;
	}
	try {
	    valorRealGasto = service.getValorRealGasto();
	} catch (ListaVaziaException e) {
	    valorRealGasto = 0;
	}
	try {
	    valorEstipuladoRestante = service.getValorEstipuladoRestante();
	} catch (ListaVaziaException e) {
	    valorEstipuladoRestante = 0;
	}
	
	StringBuilder infos = new StringBuilder();
	infos.append(!(qntProdutos == 1) ? "Você possui <strong>" + qntProdutos + "</strong> produtos na lista atual" : "Você possui <strong>" + qntProdutos + "</strong> produto na lista atual");
	infos.append("<br>");
	infos.append(!(qntProdutosComprados == 1) ? "Você já comprou <strong>" + qntProdutosComprados + "</strong> produtos de um total de <strong>" + qntProdutos + "</strong>" : "Você já comprou <strong>" + qntProdutosComprados + "</strong> produto de um total de <strong>" + qntProdutos + "</strong>" );
	infos.append("<br>");
	infos.append("Você já gastou <strong>" + currencyFormatter.format(valorRealGasto)+ "</strong>");
	infos.append("<br>");
	infos.append("Falta gastar <strong>" + currencyFormatter.format(valorEstipuladoRestante) + "</strong>");
	infos.append("<br>");
	infos.append("O valor estipulado atual é de <strong>" + currencyFormatter.format(valorEstipulado) + "</strong>");
	infos.append("<br>");
	infos.append("O valor total atual é de <strong>" + currencyFormatter.format(service.getValorTotalAtual()) + "</strong>");
	infos.append("<br>");
	infos.append("Orçamento: <strong>" + currencyFormatter.format(orcamento) + "</strong>");
	infos.append("<br>");
	return infos.toString();
    }
}
