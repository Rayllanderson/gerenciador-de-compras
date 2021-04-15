package com.ray.informacoes;

import java.text.NumberFormat;

import com.ray.model.entities.User;
import com.ray.model.util.UserUtil;

public class InformacoesUsuario {

    private UserUtil util;

    public InformacoesUsuario(User user) {
        util = new UserUtil(user);
    }

    private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    public int getNumeroTotalCategorias() {
        return util.getNumeroTotalCategorias();
    }

    public int getNumTotalProdutosComprados() {
        return util.getNumTotalProdutosComprados();
    }

    public int getNumTotalProdutos() {
        return util.getNumTotalProdutos();
    }

    public String getTotalReal() {
        return currencyFormatter.format(util.getTotalReal());
    }

    public String getTotalEstipulado() {
        return currencyFormatter.format(util.getTotalEstipulado());
    }
}
