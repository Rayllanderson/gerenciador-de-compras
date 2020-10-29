package com.ray.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.impl.AllProductJDBC;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.ProductService;
import com.ray.model.util.TotalProdutos;
import com.ray.model.validacoes.Validacao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/all-products")
public class FindAllProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AllProductJDBC productRepository = null;
    private ProductService productService = null;
    private CategoriaDao categoriaRepository = null;
    private boolean flag = false;
    private User user = null;
    private Categoria cat = null;
    private TotalProdutos totalProdutos = null;

    public FindAllProductsServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    this.user = (User) request.getSession().getAttribute("user");
	    totalProdutos = new TotalProdutos(this.user);
	    startServiceAndRepository(request, response);
	    String action = request.getParameter("action");
	    if (action != null) {
		if (action.equals("delete")) {
		    Long catId = Long.parseLong(request.getParameter("cat_id"));
		    this.cat = categoriaRepository.findById(catId);
		    productService = new ProductService(cat);
		    this.delete(request, response);
		} else if (action.equals("comprados")) {
		    listarComprados(request, response);
		} else if (action.equals("nao_comprados")) {
		    listarNaoComprados(request, response);
		} else if (action.equals("search")) {
		    search(request, response);
		} else {
		    todosProdutosDoUsuario(request, response);
		}
	    } else {
		todosProdutosDoUsuario(request, response);
	    }
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    setResponseBody(request, response, "Ocorreu um erro inesperado", 502);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    this.user = (User) request.getSession().getAttribute("user");
	    String acao = request.getParameter("action");
	    if (acao != null) {
		if (acao.equals("save")) {
		    salvarProduto(request, response);
		} else {
		    todosProdutosDoUsuario(request, response);
		}
	    } else {
		todosProdutosDoUsuario(request, response);
	    }
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    setResponseBody(request, response, "Ocorreu um erro inesperado", 502);
	}
    }

    // ---------------------------- Private methods ------------------------------//

    private void salvarProduto(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	try {
	    String nome = request.getParameter("nome");
	    String id = request.getParameter("id");
	    String valorEstipulado = request.getParameter("estipulado");
	    String valorReal = request.getParameter("real");
	    String comprado = request.getParameter("comprado");
	    Long catId = Long.parseLong(request.getParameter("cat_id"));
	    this.cat = categoriaRepository.findById(catId);
	    if (cat == null) { // verificando se o user selecionou uma categoria
		throw new CategoriaInexistenteException("Selecione uma categoria válida");
	    } else {
		startServiceAndRepository(request, response);
		// setando os valores
		Product p = new Product(!id.isEmpty() ? Long.parseLong(id) : null, nome, null, null, false, cat);
		p.setPrecoEstipulado(
			!valorEstipulado.isEmpty() ? Double.parseDouble(parseNumber(valorEstipulado)) : 0.0);
		p.setPrecoReal(!valorReal.isEmpty() ? Double.parseDouble(parseNumber(valorReal)) : 0.0);
		p.setComprado(comprado == null || comprado.equals("false") ? false : true);

		// verificação pra ver se dá update ou se insere
		if (p.getId() == null) {
		    productService.save(p);
		    response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
		    Validacao.validarCategoria(cat); // verificando se a categoria que ele vai mudar pertence ao usuario
						     // atual
		    productService.update(p);
		    response.setStatus(HttpServletResponse.SC_OK);
		}
	    }
	} catch (NumberFormatException e) {
	    setResponseBody(request, response, "Há um ou mais campos com caracteres inválidos.",
		    HttpServletResponse.SC_BAD_REQUEST);
	    // existe a chance da pessoa editar o html e mudar o id, então não vamos
	    // permitir caso a lista nao pertencer ao usuario
	} catch (CategoriaInexistenteException e) {
	    setResponseBody(request, response, e.getMessage(), 422);// dados foram compreendidos, mas não são válidos.
	} catch (EntradaInvalidaException e) {
	    setResponseBody(request, response, e.getMessage(), 400);
	} catch (NullPointerException e) { // cairá aqui caso o user tentar editar algo no html
	    setResponseBody(request, response, "Ocorreu um erro", 400);
	}

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	Long id = Long.valueOf(request.getParameter("id1"));
	if (productService.deleteById(id)) {
	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	} else {
	    response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
	}
    }

    private void todosProdutosDoUsuario(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
//	setInformacoes(request, response);
	request.getSession().setAttribute("categorias", categoriaRepository.findAll());
	request.getSession().setAttribute("produtos", productRepository.findAll());
	response.setStatus(200);
	request.getRequestDispatcher("all-products.jsp").forward(request, response);
    }

    private void listarComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    List<Product> list = totalProdutos.getComprados();
	    request.getSession().setAttribute("produtos", list);
	    response.setStatus(200);
	} catch (ListaVaziaException e) {
	    setResponseBody(request, response, e.getMessage(), 200);
	}
    }

    private void listarNaoComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    request.getSession().setAttribute("produtos", totalProdutos.getNaoComprados());
	    response.setStatus(200);
	} catch (ListaVaziaException e) {
	    setResponseBody(request, response, e.getMessage(), 200);
	}
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String serch = request.getParameter("search");
	if (!serch.isEmpty()) {
	    List<Product> list = productRepository.findByName(serch);
	    if (list.isEmpty()) {
		setResponseBody(request, response, "Nenhum produto encontrado", 400);
	    } else {
		request.getSession().setAttribute("produtos", list);
		response.setStatus(200);
		flag = true;
	    }
	} else if (flag) {
	    todosProdutosDoUsuario(request, response);
	    flag = false;
	}
    }

    // -----------------------------------------------------------------------------------------//

    /*
     * private void setInformacoes(HttpServletRequest request, HttpServletResponse
     * response) throws IOException { request.getSession().setAttribute("gerais",
     * InformacoesProdutos.infosGerais(this.cat.getUser(), util,
     * this.cat.getOrcamento())); request.getSession().setAttribute("disponivel",
     * InformacoesProdutos.getDisponivel(util, cat));
     * request.getSession().setAttribute("economizado",
     * InformacoesProdutos.getValorEconomizado(util));
     * request.getSession().setAttribute("tEstipulado",
     * InformacoesProdutos.getTotalEstipuladoHtml(util));
     * request.getSession().setAttribute("tTotal",
     * InformacoesProdutos.getValorTotalHtml(util)); }
     */

    /**
     * Apenas pra diminuir codigo. <br>
     * Seta o Response para UTf8 e o ContentType para text e manda a mensagem para o
     * Ajax
     * 
     * @param mensagem - mensagem que será enviada como resposta
     * @param codigo   - codigo que será enviado
     */
    private void setResponseBody(HttpServletRequest request, HttpServletResponse response, String mensagem, int codigo)
	    throws IOException {
	response.setContentType("text/plain");
	response.setCharacterEncoding("UTF-8");
	response.setStatus(codigo);
	response.getWriter().write(mensagem);
    }

    private String parseNumber(String value) {
	String valorParse = value.replaceAll("\\.", "");// retirando os pontos por nada
	return valorParse.replaceAll("\\,", "."); // agora só sobra a virgula, dai só mudar pra .
    }

    private void startServiceAndRepository(HttpServletRequest request, HttpServletResponse response)
	    throws IOException {
	categoriaRepository = DaoFactory.createCategoriaDao(user);
	productService = new ProductService(cat);
	productRepository = DaoFactory.createAllProductDao(user);
    }

}
