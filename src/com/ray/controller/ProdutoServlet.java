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

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.ProductoException;
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
	    setInformacoes(request, response);
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
	    request.setAttribute("error", "Antes você deve selecionar uma lista primeiro");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("categorias?acao=listar");
	    dispatcher.forward(request, response);
	}
    }

    private void setInformacoes(HttpServletRequest request, HttpServletResponse response) throws IOException {
	request.setAttribute("gerais",
		ProdutosUtil.mostrarInfosProdutos(this.cat.getUser(), service, this.cat.getOrcamento()));
	request.setAttribute("disponivel", ProdutosUtil.disponivelParaComprar(service, cat));
	request.setAttribute("economizado", ProdutosUtil.valorEconomizado(service));
	request.setAttribute("tEstipulado", ProdutosUtil.getTotalEstipuladoHtml(service));
	request.setAttribute("tTotal", ProdutosUtil.getValorTotalHtml(service));
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
		Long catOriginal = cat.getId();
		if (catOriginal != catId) { // verificando para ver se o user mudou a categoria
		    // movendo a categoria
		    cat.setId(catId);
		    service.validarCategoria(cat);
		}
		service.update(p);// moveu
		// voltando pra categoria atual para nao ser redirecionado para nova categoria
		cat.setId(catOriginal);
		response.setStatus(HttpServletResponse.SC_OK);
	    }
	} catch (NumberFormatException e) {
	    request.setAttribute("nome", nome);
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    response.getWriter().println("Digite um número válido");
	} catch (ProductoException e) { // existe a chance da pessoa editar o html e mudar o id, então não vamos
					// permitir caso a lista nao pertencer a ele
	    response.setStatus(422); // dados foram compreendidos, mas não são válidos.
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().println(e.getMessage());
	}
    }

    private String parseNumber(String value) {
	String valorParse = value.replaceAll("\\.", "");// retirando os pontos por nada
	return valorParse.replaceAll("\\,", "."); // agora só sobra a virgula, da só mudar pra .
    }

    private void startServiceAndRepository(HttpServletRequest request, HttpServletResponse response) {
	this.cat = instanciarCategoria(request);
	service = new ProductService(cat);
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
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("produtos", repository.findAll());
	dispatcher = request.getRequestDispatcher("produtos.jsp");
	dispatcher.forward(request, response);
    }

    private void listarComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	try {
	    List<Product> list = service.getProdutosConcluidos();
	    request.getSession().setAttribute("produtos", list);
	} catch (ListaVaziaException e) {
	    request.setAttribute("error", e.getMessage());
	} finally {
	    dispatcher = request.getRequestDispatcher("produtos.jsp");
	    dispatcher.forward(request, response);
	}
    }

    private void listarNaoComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	try {
	    request.getSession().setAttribute("produtos", service.getProdutosNaoConcluidos());
	} catch (ListaVaziaException e) {
	    request.setAttribute("error", e.getMessage());
	} finally {
	    dispatcher = request.getRequestDispatcher("produtos.jsp");
	    dispatcher.forward(request, response);
	}
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String serch = request.getParameter("search");
	RequestDispatcher dispatcher = null;
	try {
	    if (!serch.isEmpty()) {
		request.setAttribute("produtos", service.getProdutosByName(serch));
	    }
	} catch (ListaVaziaException e) {
	    request.setAttribute("error", e.getMessage());
	} finally {
	    dispatcher = request.getRequestDispatcher("produtos.jsp");
	    dispatcher.forward(request, response);
	}
    }
}
