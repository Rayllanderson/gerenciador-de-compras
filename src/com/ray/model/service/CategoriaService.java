package com.ray.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaException;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.ListaVaziaException;

public class CategoriaService {

    public CategoriaService(User user) {
	this.categoriaDao = DaoFactory.createCategoriaDao(user);
    }

    private CategoriaDao categoriaDao;

    // --------------------------- CRUD ----------------------------------
    /**
     * 
     * @param cat
     * @return a categoria salva no banco de dados, com id
     * @throws CategoriaException caso o nome da categoria esteja vazio
     */
    public Categoria salvar(Categoria cat) throws CategoriaException {
	try {
	   if (cat.getOrcamento() == null) {
		cat.setOrcamento(0.0);
	    }
	   validarNome(cat);
	   return categoriaDao.save(cat);
	} catch (DbException e) {
	    return null;
	}
    }
   
    public List<Categoria> findAll() {
	return categoriaDao.findAll();
    }
    
    public boolean deleteById(Long id) {
	try {
	    categoriaDao.deletById(id);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    /**
     * @param cat
     * @return true caso dê tudo ok
     * @throws CategoriaException caso o nome da categoria esteja nulo
     * @throws CategoriaInexistenteException - caso a categoria editada não exista (questão de segurança apenas, para não permitir mudar a categoria editando o html, por exemplo)
     */
    public boolean update(Categoria cat) throws CategoriaException, CategoriaInexistenteException {
	try {
	    if (cat.getOrcamento() == null) {
		cat.setOrcamento(0.0);
	    }
	    validarCategoria(cat);
	    validarNome(cat);
	    categoriaDao.update(cat);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }
    
    // -------------------------------- outros -------------------------------//
    
    public List<Categoria> searchCategoriaByName(String name) throws ListaVaziaException {
	List<Categoria> list = categoriaDao.findByName(name);
	if (list.isEmpty()) {
	    throw new ListaVaziaException(!(name.length() == 1) ? "Não existe nenhuma lista com as letras '" + name +"'": "Não existe nenhuma lista com a letra '" + name + "'");
	}
	return list;
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

    public boolean deletarCategoria(Categoria cat) {
	try {
	    categoriaDao.deletById(cat.getId());
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    public void inserirOrcamento(Categoria categoria, double value) {
	categoria.setOrcamento(value);
	categoriaDao.update(categoria);
    }
    
    //----------------------- validaçoes ----------------------------//
    
    private void validarNome(Categoria cat) throws CategoriaException {
	String nome = cat.getName();
	if (nome.trim().isEmpty() || nome == null) {
	    throw new CategoriaException("O campo 'Nome' não pode ser nulo");
	}
    }
    
    /**
     * método para verifica se a categoria atual percente ao usuário
     * 
     * @throws CategoriaInexistenteException caso a categoria escolhida não exista para o
     *                           usuário atual
     */
    public static void validarCategoria(Categoria cat) throws CategoriaInexistenteException {
	// Há uma possibilidade de editar o html e mudar a categoria, portanto, antes de
	// atualizar,
	// procura no banco de dados para verificar se o usuário é mesmo o dono da
	// categoria, se nao for, retorna nulo.
	CategoriaDao catRespotitory = DaoFactory.createCategoriaDao(cat.getUser());
	List<Categoria> list = catRespotitory.findAll();
	if (!list.contains(cat)) {
	    throw new CategoriaInexistenteException("Essa categoria não existe.");
	}
    }
}
