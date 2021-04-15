package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.ProdutoException;
import com.ray.model.validacoes.Validacao;

public class ProductService {

    protected ProductDao dao;
    protected Categoria cat;

    public ProductService(Categoria cat) {
        this.cat = cat;
        this.dao = DaoFactory.createProductDao(cat);
    }

    public Categoria getCat() {
        return cat;
    }

    // ---------------------"CRUD"-------------------------//

    /**
     * Salva o produto. Passa por 2 verifica��es. Nome nulo e pre�o nulo. Caso nome
     * seja nulo, throw exception. Caso o pre�o seja nulo, ser� setado para 0.0
     *
     * @param
     *
     * @return
     *
     * @throws EntradaInvalidaException - caso os campos valor estipalo e nome
     * estejam vazios ou nulos
     */
    public boolean save(Product p) throws EntradaInvalidaException {
        try {
            Validacao.validarNome(p.getNome());
            p.setPrecoEstipulado(Validacao.validarPreco(p.getPrecoEstipulado()));
            p.setPrecoReal(Validacao.validarPreco(p.getPrecoReal()));
            dao.save(p);
            cat.adicionarProduto(p);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Atualiza o produto. Passa por 3 verifica��es. Primeiro checa se o produto de
     * fato pertence ao usu�rio atual, caso seja, verifica se o nome n�o � nulo, se
     * for throw exception. E, os pre�os, se forem nulos, seta para 0.0
     *
     * @param p
     *
     * @return
     *
     * @throws EntradaInvalidaException - caso o campo nome esteja nulo
     * @throws ProdutoException - caso o produto n�o pertenca ao usu�rio
     */
    public boolean update(Product p) throws EntradaInvalidaException, ProdutoException {
        if (dao.productIsValid(p.getId())) {
            Validacao.validarNome(p.getNome());
            p.setPrecoEstipulado(Validacao.validarPreco(p.getPrecoEstipulado()));
            p.setPrecoReal(Validacao.validarPreco(p.getPrecoReal()));
            dao.update(p);
            return true;
        } else {
            throw new ProdutoException("Ocorreu um inesperado");
        }
    }

    /**
     * Deleta o produto pelo ID. Verifica se o produto pertence ao usu�rio, senao
     * return false
     *
     * @param id
     *
     * @return true caso ok, false caso d� erro.
     */
    public boolean deleteById(Long id) {
        try {
            if (dao.productIsValid(id)) {
                dao.deletById(id);
                return true;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return lista contendo todos os produtos. N�o trata e nem lan�a nenhuma
     * exception.
     */
    public List<Product> findAll() {
        return dao.findAll();
    }

    public List<Product> findAllWithException() throws ListaVaziaException {
        List<Product> list = dao.findAll();
        if (list.isEmpty()) {
            throw new ListaVaziaException("Ops, parece que voc� n�o tem nenhum produto na lista.");
        }
        return list;
    }

    /**
     * Procura o produto na lista atual. <br>
     * SQL: where produtos.nome LIKE '%name%' <br>
     * Ou seja, todos os produtos que conter o "nome" em qualquer lugar
     *
     * @param name
     *
     * @return
     *
     * @throws ListaVaziaException
     */
    public List<Product> findProductByName(String name) throws ListaVaziaException {
        List<Product> list = dao.findByName(name);
        if (list.isEmpty()) {
            throw new ListaVaziaException(!(name.length() == 1) ? "N�o existe nenhum produto com as letras '" + name + "'" : "N�o " +
                    "existe nenhum produto com a letra '" + name + "'");
        }
        return list;
    }

    // ---------------------------Listas-------------------------------//

    /**
     * verifica se o usuario tem protudos.
     *
     * @return true caso esteja vazia <br>
     * false contenha um ou mais produtos
     */
    public boolean listIsEmpty() {
        return dao.findAll().isEmpty() ? true : false;
    }

    // --------------------------- valida��es -----------------------------------//

}
