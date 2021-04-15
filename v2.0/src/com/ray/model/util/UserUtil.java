package com.ray.model.util;

import com.ray.model.entities.User;

public class UserUtil {

    private TotalProdutos ct;

    public UserUtil(User user) {
        this.ct = new TotalProdutos(user);
    }

    public static String formatarNome(String nome) {
        String[] name = nome.split(" ");
        return name[0].substring(0, 1).toUpperCase().concat(name[0].substring(1).toLowerCase());
    }

    public int getNumeroTotalCategorias() {
        return ct.getNumCategorias();
    }

    public int getNumTotalProdutosComprados() {
        return ct.getNumProdutosComprados();
    }

    public int getNumTotalProdutos() {
        return ct.getNumProdutos();
    }

    public Double getTotalReal() {
        return ct.getGastoComprados();
    }

    public Double getTotalEstipulado() {
        return ct.getEstipuladoComprados();
    }


}
