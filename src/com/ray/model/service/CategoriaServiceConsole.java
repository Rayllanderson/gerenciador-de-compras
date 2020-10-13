package com.ray.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaException;
import com.ray.model.exception.ListaVaziaException;

public class CategoriaServiceConsole extends CategoriaService{

    public CategoriaServiceConsole(User user) {
	super(user);
    }


    /**
     * @throws ListaVaziaException ja com mensagem
     */
    public void listarCategoriasConsole() throws ListaVaziaException {
	List<Categoria> list = new ArrayList<>();
	list = categoriaDao.findAll();
	if (!list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		System.out.println("[ " + (i + 1) + " ] - " + list.get(i));
	    }
	} else {
	    throw new ListaVaziaException("Lista Vazia");
	}
    }

    /**
     * Método para escolher a categoria via console
     * @param numero da categoria
     * @return a categoria escolhida
     * @throws CategoriaException se nao encontrar categoria com o número digitado.
     *                            Exception já contem mensagem
     */
    public Categoria getCategoriaByNumber(int num) throws CategoriaException {
	List<Categoria> list = categoriaDao.findAll();
	if (!list.isEmpty() && num <= list.size()) {
	    return list.get(num - 1);
	}
	throw new CategoriaException("Não encontrado. Verifique se digitou corretamente");
    }

    public void inserirOrcamento(Categoria categoria, double value) {
	categoria.setOrcamento(value);
	categoriaDao.update(categoria);
    }
    
}
