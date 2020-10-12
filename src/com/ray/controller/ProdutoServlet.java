package com.ray.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.informacoes.InformacoesProdutos;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.CategoriaService;
import com.ray.model.service.ProductService;
import com.ray.model.util.ProdutosUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDao repository = null;
    private ProductService service = null;
    private Categoria cat = null;
    private boolean flag = false;
    private ProdutosUtil util = null;

    public ProdutoServlet() {
	super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
//	    System.out.println("método GET...");
	    String acao = request.getParameter("acao");
//	    System.out.println(acao);
	    startServiceAndRepository(request, response);
	    if (acao != null) {
		if (acao.equals("listar")) {
		    listarTodosProdutos(request, response);
		} else if (acao.equals("comprados")) {
		    listarComprados(request, response);
		} else if (acao.equals("nao_comprados")) {
		    listarNaoComprados(request, response);
		} else if (acao.equals("excluir")) {
		    excluir(request, response);
		} else if (acao.equals("search")) {
		    search(request, response);
		}
	    } else {
		listarTodosProdutos(request, response);
	    }
	} catch (NullPointerException e) {
	    request.setAttribute("catNula", "Antes você deve selecionar uma lista primeiro");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("categorias?acao=listar");
	    dispatcher.forward(request, response);
	}
    }

    private void setInformacoes(HttpServletRequest request, HttpServletResponse response) throws IOException {
	request.getSession().setAttribute("gerais", InformacoesProdutos.mostrarInfosProdutos(this.cat.getUser(), util, this.cat.getOrcamento()));
	request.getSession().setAttribute("disponivel", InformacoesProdutos.disponivelParaComprar(util, cat));
	request.getSession().setAttribute("economizado", InformacoesProdutos.valorEconomizado(util));
	request.getSession().setAttribute("tEstipulado", InformacoesProdutos.getTotalEstipuladoHtml(util));
	request.getSession().setAttribute("tTotal", InformacoesProdutos.getValorTotalHtml(util));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    startServiceAndRepository(request, response);
	    setInformacoes(request, response);
//	    System.out.println(acao + " método POST");
	    if (acao.equals("listar")) {
		listarTodosProdutos(request, response);
	    } else if (acao.equals("selecionar")) {
		String id = request.getParameter("id");
		Product p = repository.findById(Long.parseLong(id));
		System.out.println(p);
	    } else if (acao.equals("salvar")) {
		salvarProduto(request, response);
	    } else if (acao.equals("editar")) {
		redirecionarEditarProduto(request, response);
	    }
	} else {
	    listarTodosProdutos(request, response);
	}
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException {
	Long id = Long.valueOf(request.getParameter("id1"));
	if (service.deleteById(id)) {
	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	} else {
	    response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
	}

    }

    private void redirecionarEditarProduto(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String id = request.getParameter("id");
	Product p = repository.findById(Long.parseLong(id));
	RequestDispatcher dispatcher = request.getRequestDispatcher("edit-produto.jsp");
	request.setAttribute("produto", p);
	dispatcher.forward(request, response);
    }

    private void salvarProduto(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	String id = request.getParameter("id");
	String nome = request.getParameter("nome");
	
	Long catOriginal = cat.getId();
	try {
	    String valorEstipulado = request.getParameter("estipulado");
	    String valorReal = request.getParameter("real");
	    String comprado = request.getParameter("comprado");

	    Product p = new Product(!id.isEmpty() ? Long.parseLong(id) : null, nome, null, null, false, cat);
	    p.setPrecoEstipulado(Double.parseDouble(parseNumber(valorEstipulado)));
	    p.setPrecoReal(!valorReal.isEmpty() ? Double.parseDouble(parseNumber(valorReal)) : 0.0);
	    p.setComprado(comprado == null || comprado.equals("false") ? false : true);

	    if (p.getId() == null) {
		service.inserir(p);
		response.setStatus(HttpServletResponse.SC_CREATED);
	    } else {
		Long catId = Long.parseLong(request.getParameter("cat_id"));
//		System.out.println("ci " + catId);
		if (catOriginal != catId) { // verificando para ver se o user mudou a categoria
		    // movendo a categoria
		    cat.setId(catId);
		    CategoriaService.validarCategoria(cat); //verificando se a categoria que ele vai mudar pertence a ele mesmo
		}
		service.update(p);// moveu
		// voltando pra categoria atual para nao ser redirecionado para nova categoria
		cat.setId(catOriginal);
		response.setStatus(HttpServletResponse.SC_OK);
	    }
	} catch (NumberFormatException e) {
	    setResponseBody(request, response, "Valor inválido para o campo 'Valor Estipulado'", HttpServletResponse.SC_BAD_REQUEST);
	} catch (CategoriaInexistenteException e) { // existe a chance da pessoa editar o html e mudar o id, então não vamos
					// permitir caso a lista nao pertencer a ele	
	    cat.setId(catOriginal);
	    setResponseBody(request, response, e.getMessage(), 422);// dados foram compreendidos, mas não são válidos.
	} catch (EntradaInvalidaException e) {
	    setResponseBody(request, response, e.getMessage(), 400);
	} 
    }

    /**
     * Apenas pra diminuir codigo. <br>
     * Seta o Response para UTf8 e o ContentType para text e manda a mensagem para o Ajax
     * @param mensagem - mensagem que será enviada como resposta 
     * @param codigo - codigo que será enviado
     */
    private void setResponseBody(HttpServletRequest request, HttpServletResponse response, String mensagem, int codigo) throws IOException {
	response.setContentType("text/plain");
	response.setCharacterEncoding("UTF-8");
	response.setStatus(codigo);
	response.getWriter().write(mensagem);
    }

    private String parseNumber(String value) {
	String valorParse = value.replaceAll("\\.", "");// retirando os pontos por nada
	return valorParse.replaceAll("\\,", "."); // agora só sobra a virgula, da só mudar pra .
    }

    private void startServiceAndRepository(HttpServletRequest request, HttpServletResponse response) {
	this.cat = instanciarCategoria(request);
	service = new ProductService(cat);
	util = new ProdutosUtil(cat);
	repository = DaoFactory.createProductDao(cat);
    }

    private Categoria instanciarCategoria(HttpServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	this.cat = (Categoria) session.getAttribute("categoria");
	return cat;
    }

    private void listarTodosProdutos(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	setInformacoes(request, response);
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("produtos", repository.findAll());
	dispatcher = request.getRequestDispatcher("produtos.jsp");
	response.setStatus(200);
	dispatcher.forward(request, response);
    }

    private void listarComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    List<Product> list = util.getConcluidos();
	    request.getSession().setAttribute("produtos", list);
	    response.setStatus(200);
	} catch (ListaVaziaException e) {
	    setResponseBody(request, response, e.getMessage(), 200);
	}
    }

    private void listarNaoComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    request.getSession().setAttribute("produtos", util.getNaoConcluidos());
	    response.setStatus(200);
	} catch (ListaVaziaException e) {
	    setResponseBody(request, response, e.getMessage(), 200);
	}
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String serch = request.getParameter("search");
	try {
	    if (!serch.isEmpty()) {
		request.getSession().setAttribute("produtos", service.searchProductByName(serch));
		response.setStatus(200);
		flag = true;
	    } else if (flag) {
		listarTodosProdutos(request, response);
		flag = false;
	    }
	} catch (ListaVaziaException e) {
	    setResponseBody(request, response, e.getMessage(), 400);
	}
    }
}
