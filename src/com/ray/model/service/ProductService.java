package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.ProductoException;
import com.ray.model.util.FormatarTabela;

public class ProductService {

    private ProductDao dao;
    private Categoria cat;

    public ProductService(Categoria cat) {
	this.cat = cat;
	this.dao = DaoFactory.createProductDao(cat);
    }

    public Categoria getCat() {
	return cat;
    }

    // ---------------------"CRUD"-------------------------//
    public boolean inserir(Product p) {
	try {
	    dao.save(p);
	    cat.adicionarProduto(p);
	    return true;
	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean update(Product p) {
	try {
		dao.update(p);
		return true;

	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean deletar(Product p) {
	try {
	    dao.deletById(p.getId());
	    cat.deletarProduto(p);
	    return true;
	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public void deleteById(Long id) {
	try {
	    dao.deletById(id);
	} catch (DbException e) {
	    e.printStackTrace();
	}
    }

    public void editarNome(Product p, String nome) {
	p.setNome(nome);
	dao.update(p);
    }

    public void editarPrecoEstipulado(Product p, double value) {
	p.setPrecoEstipulado(value);
	dao.update(p);
    }

    public void editarPrecoReal(Product p, double value) {
	p.setPrecoReal(value);
	dao.update(p);
    }

    public void marcarComoConcluido(Product p, double value) {
	p.setPrecoReal(value);
	if (!(p.getId() == null)) {
	    p.setComprado(true);
	    dao.update(p);
	} else {
	    p.setComprado(true);
	}
    }

    public void marcarComoNaoConcluido(Product p, double value) {
	p.setPrecoReal(value);
	if (!(p.getId() == null)) {
	    p.setComprado(false);
	    dao.update(p);
	} else {
	    p.setPrecoReal(value);
	}
    }

    public void mudarCategoria(Product p, Categoria cat) throws ProductoException{
	p.setCategoria(cat);
	dao.update(p);
   }
    
    /**
     * método para verifica se a categoria atual percente ao usuário
     * @throws ProductoException caso a categoria escolhida não exista para o usuário atual
     */
    public void validarCategoria(Categoria cat) throws ProductoException{
	 //Há uma possibilidade de editar o html e mudar a categoria, portanto, antes de atualizar,
	//procura no banco de dados para verificar se o usuário é mesmo o dono da categoria, se nao for, retorna nulo. 
	CategoriaDao catRespotitory = DaoFactory.createCategoriaDao(this.cat.getUser());
	List<Categoria> list = catRespotitory.findAll();
	if(!list.contains(cat)) {
	    throw new ProductoException("Essa categoria não existe.");
	}
    }

    // ---------------------------Listas-------------------------------//

    public List<Product> findAllProduct() {
	return dao.findAll();
    }
    
    /**
     * verifica se a lista de produtos está vazia
     * @return true caso esteja vazia <br>false caso não esteja
     */
    public boolean listIsEmpty() {
	return dao.findAll().isEmpty() ? true : false;
    }
   
    /**
     * @throws ListaVaziaException("Ops, parece que você não tem nenhum produto na
     *                                   lista.");
     */
    public void listarPordutos() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 6);
	for (int i = 0; i < list.size(); i++) {
	    FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	}
    }

    public List<Product> findAll() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	return list;
    }

    public void listarNaoConcluidos() throws ListaVaziaException {
	List<Product> list = this.getProdutosNaoConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (!(list.get(i).isComprado())) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }

    public void listarConcluidos() throws ListaVaziaException {
	List<Product> list = this.getProdutosConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i).isComprado()) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }

    public List<Product> getProdutosNaoConcluidos() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.size() == 0) {
	    throw new ListaVaziaException("Você não tem produtos na lista");
	}
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i).isComprado()) {
		list.remove(i);
		i--;
	    }
	}
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Todos os produtos da lista foram comprados :)");
	}
	return list;
    }

    /**
     * 
     * @return uma lista de todos os produtos que foram concluídos
     * @throws ListaVaziaException caso não possua nenhum produto na lista ou caso nenhum produto da lista tenha sido comprado <br>
     * ListaVaziaException("Você não tem produtos na lista"); <br>
     * ListaVaziaException("Puxa, nenhum produto foi comprado até o momento :(");
     */
    public List<Product> getProdutosConcluidos() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.size() == 0) {
	    throw new ListaVaziaException("Você não tem produtos na lista");
	}
	for (int i = 0; i < list.size(); i++) {
	    if (!(list.get(i).isComprado())) {
		list.remove(i);
		i--;
	    }
	}
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Puxa, nenhum produto foi comprado até o momento :(");
	}
	return list;
    }

    public List<Product> getProdutosByName(String name) throws ListaVaziaException {
	List<Product> list = dao.findByName(name);
	if (list.isEmpty()) {
	    throw new ListaVaziaException(
		    !(name.length() == 1) ? "Não existe nenhum produto com as letras '" + name + "'"
			    : "Não existe nenhum produto com a letra '" + name + "'");
	}
	return list;
    }

    // -----------------------------SOMAS--------------------------------------//
    
    /**
     * 
     * @return valor total real dos produtos (mesmo que não estejam marcados como comprado)
     * @throws ListaVaziaException caso não tenha nenhum produto na lista
     */
    public double getValorRealGasto() throws ListaVaziaException {
	double sum = 0;
	List<Product> list = this.getProdutosConcluidos();
	for (Product p : list) {
	    sum += p.getPrecoReal();
	}
	return sum;
    }

    /**
     * 
     * @return valor estipulado total de todos os produtos
     * @throws ListaVaziaException caso não tenha nenhum produto na lista
     */
    public double getValorTotalEstipulado() throws ListaVaziaException {
	double sum = 0;
	List<Product> list = dao.findAll();
	for (Product p : list) {
	    sum += p.getPrecoEstipulado();
	}
	return sum;
    }

    /**
     * 
     * @return valor total. Soma dos produtos comprados. Se um produto não tiver sido comprado, considera o valor estipulado<br>
     *  valorReal + valorEstipulado (if !comprado)
     */
    public double getValorTotalAtual() {
	double sum = 0;
	List<Product> list = dao.findAll();
	for (Product p : list) {
	    if (p.getPrecoReal() != 0) {
		sum += p.getPrecoReal();
	    } else {
		sum += p.getPrecoEstipulado();
	    }
	}
	return sum;
    }

    
    /**
     * 
     * @param service
     * @return a soma total do valor estipulado para os produtos que não foram comprados
     * Se todos os produtos forem comprados, retorna 0
     */
    public double getValorEstipuladoRestante() {
	try {
	    List<Product> list = getProdutosNaoConcluidos();
	    return list.stream().mapToDouble(Product::getPrecoEstipulado).sum();
	}catch (ListaVaziaException e) {
	    return 0.0;
	}
    }

    // ----------------------------úteis---------------------------------------//

    /**
     * 
     * @return PrecoEstipulado - PrecoReal;
     * @throws ListaVaziaException caso não tenha nenhum produto comprado
     */
    public double getValorEconomizado() throws ListaVaziaException {
	double total = 0;
	List<Product> list = this.getProdutosConcluidos();
	for (Product p : list) {
	    total += p.getPrecoEstipulado() - p.getPrecoReal();
	}
	return total;
    }

    /**
     * 
     * @param cat
     * @return total = orcamento - this.getValorRealGasto();
     * @throws NullPointerException
     * @throws ListaVaziaException quando não há produtos na lista
     */
    public double getValorDisponivelParaCompra(Categoria cat) throws NullPointerException, ListaVaziaException{
	double total = 0;
	double orcamento = cat.getOrcamento();
	try{
	    total = orcamento - this.getValorRealGasto();
	}catch (ListaVaziaException e) {
	    
	}
	return total;
    }

}
