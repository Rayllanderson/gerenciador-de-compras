package com.ray.informacoes;

import java.text.NumberFormat;
import java.util.Locale;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.util.HtmlColors;
import com.ray.model.util.ProdutosUtil;


public class InformacoesProdutos {

    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    // ---------------------------------------------------------------------//

    /**
     * @apiNote Exceptions tratadas nesse m�todo: NullPointerException,
     * ListaVaziaException
     */
    public static String getQuantidadeGasta(ProdutosUtil util, Categoria cat) {
        try {
            double orcamento = cat.getOrcamento();
            double valorGasto = util.getTotalGasto();
            String complemento = ". ";
            if (orcamento == 0) {
                complemento = " E voc� n�o tem um orcamento para essa lista";
            } else {
                if (valorGasto > orcamento) {
                    complemento += "Voc� j� ultrapassou seu or�amento em R$" + (valorGasto - orcamento);
                } else {
                    complemento += "Voc� ainda tem " + currencyFormatter.format((orcamento - valorGasto)) + " para gastar de acordo " +
                            "com seu or�amento de " + currencyFormatter.format(orcamento);
                }
            }
            return "Voc� j� gastou " + currencyFormatter.format(valorGasto) + complemento;
        } catch (NullPointerException e) {
            return "Voc� n�o tem or�amento para esta lista. Adicione um no menu principal";
        } catch (ListaVaziaException e) {
            return "Nenhum produto comprado at� o momento, sendo assim, voc� n�o gastou nada.";
        }
    }

    /**
     * @apiNote Exceptions tratadas nesse m�todo: NullPointerException,
     * ListaVaziaException
     */
    public static String getDisponivel(ProdutosUtil util, Categoria cat) {
        Double orcamento = 0.0;
        double somaTotalNaoConcluidos = util.getEstipuladoRestante();
        if (somaTotalNaoConcluidos == 0 && !util.getAll().isEmpty() && util.sizeOfConcluidos() > 0) {
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
                complemento = HtmlColors.RED + " Ixi! Voc� passou do seu orcamento em " + currencyFormatter.format((-(disponivel)));
                complemento += ". Voc� n�o tem mais nada dispon�vel para gastar";
                complemento += ". Or�amento para lista " + cat.getName() + ": " + currencyFormatter.format(orcamento);
            } else {
                if (disponivel > 0) {
                    complemento = HtmlColors.GREEN + " Voc� tem dispon�vel " + currencyFormatter.format(disponivel) + ", de acordo " +
                            "com seu or�amento para lista " + cat.getName();
                    double valorTotalNaoConcluidos = disponivel - somaTotalNaoConcluidos;
                    if (valorTotalNaoConcluidos != 0) {

                        if (valorTotalNaoConcluidos < 0) {
                            complemento += HtmlColors.RED + "<br> Se comprar todos os produtos da lista, passar� em " + currencyFormatter.format(-(valorTotalNaoConcluidos)) + " do seu orcamento";
                        } else {
                            complemento += "<br> Se comprar todos os produtos da lista, ficar� com " + currencyFormatter.format(valorTotalNaoConcluidos);
                        }
                    }
                } else if (disponivel == 0 && somaTotalNaoConcluidos == 0) {
                    complemento += "Nada! Voc� atingiu sua meta e comprou todos os produtos exatamente de acordo com seu or�amento! " +
                            "Seguiu a lista risca, Parab�ns!";
                } else {
                    complemento += "Voc� antigiu seu or�amento e a partir de agora, tudo passar� de seu orcamento" + HtmlColors.RED + "<br>Comprando tudo, ao final, passar� em " + currencyFormatter.format(somaTotalNaoConcluidos) + " do seu or�amento." + "<br>Totalizando " + currencyFormatter.format(somaTotalNaoConcluidos + util.getTotalGasto());
                }
            }
            complemento += HtmlColors.BLACK;
            return complemento;
        } catch (ListaVaziaException e) {
            if (orcamento == 0.0 || orcamento == null) {
                return "Voc� n�o tem or�amento para esta lista, portanto, imposs�vel saber quanto ainda tem dispon�vel para compra :" +
                        "( . Adicione um or�amento no menu principal";
            } else
                return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado at� o momento :(") ?
                        HtmlColors.GREEN + "Voc� ainda n�o comprou nenhum produto da lista. Ent�o voc� ainda tem " + currencyFormatter.format(orcamento) + " dispon�vel para gastar" + HtmlColors.BLACK : e.getMessage();
        } catch (NullPointerException e) {
            return "Voc� n�o tem or�amento para esta lista. Por isso, infelizmente, n�o � poss�vel saber o quanto voc� ainda tem " +
                    "dispon�vel. Adicione um or�amento <a class=\"text-decoration-none\" type=\"button\"onclick=\"document" +
                    ".getElementById('editCat').click()\"> aqui </a>.";
        }
    }


    /**
     * @apiNote Exceptions tratadas nesse m�todo: ListaVaziaException
     */
    public static String getValorEconomizado(ProdutosUtil util) {
        try {
            double valorEconomizado = util.getValorEconomizado();
            if (valorEconomizado < 0) {
                return HtmlColors.RED + "Eita! Voc� n�o economizou nada! Voc� gastou " + currencyFormatter.format((-(valorEconomizado))) + " a mais do que planejava" + HtmlColors.BLACK;
            } else if (valorEconomizado == 0) {
                return "At� o momento, voc� est� seguindo sua lista a risca! N�o economizou nada e tamb�m n�o gastou mais do que " +
                        "deveria. Est� indo bem!";
            }
            return HtmlColors.GREEN + "Voc� economizou " + currencyFormatter.format(valorEconomizado) + " Parab�ns!" + HtmlColors.BLACK;
        } catch (ListaVaziaException e) {
            return e.getMessage().isEmpty() || e.getMessage().equals("Puxa, nenhum produto foi comprado at� o momento :(") ? "Voc� " +
                    "ainda n�o comprou nenhum produto da lista. No momento, imposs�vel saber valor economizado :(" : e.getMessage();
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
        } catch (ListaVaziaException e) {
            totalAtual = 0;
        }
        try {
            valorEstipulado = util.getTotalEstipulado();
        } catch (ListaVaziaException e) {
            valorEstipulado = 0;
        }
        try {
            qntProdutos = util.getAll().size();
        } catch (ListaVaziaException e) {
            qntProdutos = 0;
        }
        try {
            qntProdutosComprados = util.getConcluidos().size();
        } catch (ListaVaziaException e) {
            qntProdutosComprados = 0;
        }
        try {
            valorRealGasto = util.getTotalGasto();
        } catch (ListaVaziaException e) {
            valorRealGasto = 0;
        }
        try {
            valorEstipuladoRestante = util.getEstipuladoRestante();
        } catch (ListaVaziaException e) {
            valorEstipuladoRestante = 0;
        }

        StringBuilder infos = new StringBuilder();
        infos.append(!(qntProdutos == 1) ? "Voc� possui <strong>" + HtmlColors.BLUE + qntProdutos + HtmlColors.BLACK + "</strong> " +
                "produtos na lista atual" : "Voc� possui <strong>" + HtmlColors.BLUE + qntProdutos + HtmlColors.BLACK + "</strong> " +
                "produto na lista atual");
        infos.append("<br>");
        infos.append(!(qntProdutosComprados == 1) ?
                "Voc� j� comprou <strong>" + HtmlColors.BLUE + qntProdutosComprados + HtmlColors.BLACK + "</strong> produtos de um " +
                        "total de <strong>" + HtmlColors.PINK + qntProdutos + HtmlColors.BLACK + "</strong>" : "Voc� j� comprou " +
                "<strong>" + HtmlColors.BLUE + qntProdutosComprados + HtmlColors.BLACK + "</strong> produto de um total de <strong>" + HtmlColors.PINK + qntProdutos + HtmlColors.BLACK + "</strong>");
        infos.append("<br>");
        infos.append("Voc� j� gastou <strong>" + HtmlColors.BLUE + currencyFormatter.format(valorRealGasto) + HtmlColors.BLACK +
                "</strong>");
        infos.append("<br>");
        infos.append("Falta gastar <strong>" + HtmlColors.PINK + currencyFormatter.format(valorEstipuladoRestante) + HtmlColors.BLACK + "</strong>");
        infos.append("<br>");
        infos.append("O valor estipulado atual � de <strong>" + HtmlColors.PINK + currencyFormatter.format(valorEstipulado) + HtmlColors.BLACK + "</strong>");
        infos.append("<br>");
        infos.append("O valor total atual � de <strong>" + HtmlColors.BLUE + currencyFormatter.format(totalAtual) + HtmlColors.BLACK + "</strong>");
        infos.append("<br>");
        infos.append("Or�amento: <strong>" + HtmlColors.PURPLE + currencyFormatter.format(orcamento) + HtmlColors.BLACK + "</strong>");
        infos.append("<br>");
        return infos.toString();
    }

    // ----------------------- console --------------------------------//

    public static void somaTotalConsole(ProdutosUtil util) {
        System.out.println("Valor Total Estipulado: " + currencyFormatter.format(util.getTotalEstipulado()));
        System.out.println("Valor Total: " + currencyFormatter.format(util.getTotalAtual()));
    }

}
