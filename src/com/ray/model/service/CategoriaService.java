package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.validacoes.Validacao;

public class CategoriaService {

    public CategoriaService(User user) {
	this.categoriaDao = DaoFactory.createCategoriaDao(user);
    }

    protected CategoriaDao categoriaDao;

    // --------------------------- CRUD ----------------------------------
    /**
     * Para salvar, passa por 2 verificações. Se o preço é nulo, seta para 0. Se o
     * nome é nulo, throw exception.
     * 
     * @param cat
     * @return a categoria salva no banco de dados, com id
     * @throws EntradaInvalidaException - caso o nome da categoria esteja nulo
     */
    public Categoria save(Categoria cat) throws EntradaInvalidaException {
	try {
	    cat.setOrcamento(Validacao.validarPreco(cat.getOrcamento()));
	    Validacao.validarNome(cat.getName());
	    return categoriaDao.save(cat);
	} catch (DbException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * @param id
     * @return true caso de tudo ok, false caso a categoria não pertenca ao usuário ou ocorra um erro 
     */
    public boolean deleteById(Long id)  {
	try {
	    // verificando se a categoria pertence ao usuário
	    Validacao.validarCategoria(categoriaDao.findById(id));
	    categoriaDao.deletById(id);
	    return true;
	} catch (DbException | CategoriaInexistenteException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * Para atualizar, passa por 3 verificações. Se a categoria, de fato, pertence ao usuario atual. Verifica se o preço é nulo, se for,  seta para 0. E, verifica se
     * o nome é nulo, se for, throw exception.
     * 
     * @return true caso dê tudo ok
     * @throws CategoriaInexistenteException caso a categoria editada não exista
     *                                       (questão de segurança apenas, para não
     *                                       permitir mudar a categoria editando o
     *                                       html, por exemplo)
     * @throws EntradaInvalidaException      caso o nome da categoria esteja nulo
     */
    public boolean update(Categoria cat) throws CategoriaInexistenteException, EntradaInvalidaException {
	try {
	    cat.setOrcamento(Validacao.validarPreco(cat.getOrcamento()));
	    Validacao.validarCategoria(cat);
	    Validacao.validarNome(cat.getName());
	    categoriaDao.update(cat);
	    return true;
	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * 
     * @return todas as categorias que pertencem ao usuário atual. Não lança nenhuma 
     *  exception
     */
    public List<Categoria> findAll() {
	return categoriaDao.findAll();
    }

    /**
     * 
     * @param name
     * @return uma lista contendo todas as categorias que contém, em qualquer parte, o nome no paramentro. Podendo ser apenas uma letra ou não
     * @throws ListaVaziaException
     */
    public List<Categoria> findCategoriaByName(String name) throws ListaVaziaException {
	List<Categoria> list = categoriaDao.findByName(name);
	if (list.isEmpty()) {
	    throw new ListaVaziaException(
		    !(name.length() == 1) ? "Não existe nenhuma lista com as letras '" + name + "'"
			    : "Não existe nenhuma lista com a letra '" + name + "'");
	}
	return list;
    }

}
