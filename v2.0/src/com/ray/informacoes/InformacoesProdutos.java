package com.ray.informacoes;

import java.text.NumberFormat;
import java.util.Locale;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.util.HtmlColors;
import com.ray.model.util.ProdutosUtil;


public class InformacoesProdutos {
    
    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
   
    // ---------------------------------------------------------------------//
    
    /**
     * @apiNote Exceptions tratadas nesse método: NullPointerException,
     *          ListaVaziaException
     */
    public static String getQuantidadeGasta(ProdutosUtil util, Categoria cat) {
	try {
	    double orcamento = cat.getOrcamento();
	    double valorGasto = util.getTotalGasto();
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
    public static String getDisponivel(ProdutosUtil util, Categoria cat) {
	 Double orcamento = 0.0;
	 double somaTotalNaoConcluidos = util.getEstipuladoRestante();
	 if(somaTotalNaoConcluidos == 0 && !util.getAll().isEmpty() && util.sizeOfConcluidos() >0) {
	     return "Todos os produtos foram comprados :)";
	 }
	try {
	    double disponivel = 0;
	    String complemento = "";
	    orcamento = cat.getOrcamento();
	    if (orcamento == 0.0 || orcamento == null) {
		throw new NullPointerException();
	    }
	    disponivel = orcamento - util.getTotalGasto();
	    if (disponivel < 0) {
		complemento = HtmlColors.RED +" Ixi! Você passou do seu orcamento em " + currencyFormatter.format((-(disponivel)));
		complemento += ". Você não tem mais nada disponível para gastar";
		complemento += ". Orçamento para lista " + cat.getName() + ": "
			+ currencyFormatter.format(orcamento);
	    } else {
		if(disponivel > 0) {
		complemento = HtmlColors.GREEN +" Você tem disponível " + currencyFormatter.format(disponivel)
			+ ", de acordo com seu orçamento para lista " + cat.getName();
		double valorTotalNaoConcluidos = disponivel - somaTotalNaoConcluidos;
		if(valorTotalNaoConcluidos != 0) {
		   
		    if (valorTotalNaoConcluidos < 0) {
			complemento += HtmlColors.RED + "<br> Se comprar todos os produtos da lista, passará em " + currencyFormatter.format(-(valorTotalNaoConcluidos)) + " do seu orcamento";
		    }else {
			complemento += "<br> Se comprar todos os produtos da lista, ficará com " + currencyFormatter.format(valorTotalNaoConcluidos);
		    }
		}
		}else if (disponivel == 0 && somaTotalNaoConcluidos == 0){
		complemento += "Nada! Você atingiu sua meta e comprou todos os produtos exatamente de acordo com seu orçamento! Seguiu a lista risca, Parabéns!";
		}else {
		complemento += "Você antigiu seu orçamento e a partir de agora, tudo passará de seu orcamento"
			+ HtmlColors.RED + "<br>Comprando tudo, ao final, passará em " + currencyFormatter.format(somaTotalNaoConcluidos) + " do seu orçamento."
				+ "<br>Totalizando " + currencyFormatter.format(somaTotalNaoConcluidos + util.getTotalGasto());
		}
	    }
	    complemento += HtmlColors.BLACK;
	    return complemento;
	} catch (ListaVaziaException e) {
	    if (orcamento == 0.0 || orcamento == null) {
		return "Você não tem orçamento para esta lista, portanto, impossível saber quanto ainda tem disponível para compra :( . Adicione um orçamento no menu principal";
	    } else 
		return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado até o momento :(") ? HtmlColors.GREEN + "Você ainda não comprou nenhum produto da lista. Então você ainda tem "
			+ currencyFormatter.format(orcamento) + " disponível para gastar" + HtmlColors.BLACK : e.getMessage();
	} catch (NullPointerException e) {
	    return "Você não tem orçamento para esta lista. Por isso, infelizmente, não é possível saber o quanto você ainda tem disponível. Adicione um orçamento <a class=\"text-decoration-none\" type=\"button\"onclick=\"document.getElementById('editCat').click()\"> aqui </a>.";
	}
    }


    
    /**
     * @apiNote Exceptions tratadas nesse método: ListaVaziaException
     */
    public static String getValorEconomizado(ProdutosUtil util) {
	try {
	    double valorEconomizado = util.getValorEconomizado();
	    if (valorEconomizado < 0) {
		return HtmlColors.RED +"Eita! Você não economizou nada! Você gastou " + currencyFormatter.format((-(valorEconomizado)))
			+ " a mais do que planejava" + HtmlColors.BLACK;
	    }else if(valorEconomizado == 0) {
		return "Até o momento, você está seguindo sua lista a risca! Não economizou nada e também não gastou mais do que deveria. Está indo bem!";
	    }
	    return HtmlColors.GREEN + "Você economizou " + currencyFormatter.format(valorEconomizado) + " Parabéns!" + HtmlColors.BLACK;
	} catch (ListaVaziaException e) {
	    return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado até o momento :(") ? "Você ainda não comprou nenhum produto da lista. No momento, impossível saber valor economizado :(" : e.getMessage();
	}
    }
    
    public static String getTotalEstipuladoHtml(ProdutosUtil util) {
	return HtmlColors.BLUE + currencyFormatter.format(util.getTotalEstipulado()) + HtmlColors.BLACK;
    }
    
    public static String getValorTotalHtml(ProdutosUtil util) {
   	return HtmlColors.BLUE + currencyFormatter.format(util.getTotalAtual()) + HtmlColors.BLACK;
    }
    
    
    public static String infosGerais(User user, ProdutosUtil util, double orcamento) {
	int qntProdutos = 0, qntProdutosComprados = 0;
	double valorRealGasto = 0, valorEstipulado = 0, valorEstipuladoRestante = 0, totalAtual = 0;
	try {
	    totalAtual = util.getTotalAtual();
	}catch (ListaVaziaException e) {
	    totalAtual = 0;
	}
	try {
	    valorEstipulado = util.getTotalEstipulado();
	}catch (ListaVaziaException e) {
	    valorEstipulado = 0;
	}
	try {
	    qntProdutos = util.getAll().size();
	}catch (ListaVaziaException e) {
	    qntProdutos = 0;
	}
	try {
	    qntProdutosComprados = util.getConcluidos().size();
	}catch (ListaVaziaException e) {
	    qntProdutosComprados = 0;
	}
	try {
	    valorRealGasto = util.getTotalGasto();
	}catch (ListaVaziaException e) {
	    valorRealGasto = 0;
	}
	try {
	    valorEstipuladoRestante = util.getEstipuladoRestante();
	}catch (ListaVaziaException e) {
	    valorEstipuladoRestante = 0;
	}
		
	StringBuilder infos = new StringBuilder();
	infos.append(!(qntProdutos == 1) ? "Você possui <strong>" + HtmlColors.BLUE  + qntProdutos + HtmlColors.BLACK + "</strong> produtos na lista atual": "Você possui <strong>"  + HtmlColors.BLUE  + qntProdutos  + HtmlColors.BLACK + "</strong> produto na lista atual" );
	infos.append("<br>");
	infos.append(!(qntProdutosComprados == 1) ? "Você já comprou <strong>" + HtmlColors.BLUE  + qntProdutosComprados + HtmlColors.BLACK  +"</strong> produtos de um total de <strong>" + HtmlColors.PINK  + qntProdutos + HtmlColors.BLACK  +"</strong>" : "Você já comprou <strong>" +HtmlColors.BLUE  + qntProdutosComprados + HtmlColors.BLACK  +"</strong> produto de um total de <strong>" + HtmlColors.PINK  +qntProdutos + HtmlColors.BLACK  +"</strong>" );
	infos.append("<br>");
	infos.append("Você já gastou <strong>" + HtmlColors.BLUE  +currencyFormatter.format(valorRealGasto)+ HtmlColors.BLACK  +"</strong>");
	infos.append("<br>");
	infos.append("Falta gastar <strong>" + HtmlColors.PINK  +currencyFormatter.format(valorEstipuladoRestante) + HtmlColors.BLACK  +"</strong>");
	infos.append("<br>");
	infos.append("O valor estipulado atual é de <strong>" + HtmlColors.PINK  +currencyFormatter.format(valorEstipulado) + HtmlColors.BLACK  + "</strong>");
	infos.append("<br>");
	infos.append("O valor total atual é de <strong>" + HtmlColors.BLUE  +currencyFormatter.format(totalAtual) + HtmlColors.BLACK  +"</strong>");
	infos.append("<br>");
	infos.append("Orçamento: <strong>" + HtmlColors.PURPLE  + currencyFormatter.format(orcamento) + HtmlColors.BLACK  +"</strong>");
	infos.append("<br>");
	return infos.toString();
    }
    
    // ----------------------- console --------------------------------//
    
    public static void somaTotalConsole(ProdutosUtil util) {
	System.out.println("Valor Total Estipulado: " + currencyFormatter.format(util.getTotalEstipulado()));
	System.out.println("Valor Total: " + currencyFormatter.format(util.getTotalAtual()));
    }
    
}
