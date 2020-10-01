package com.ray.controller;

import java.io.IOException;

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
	System.out.println("método GET...");
	String acao = request.getParameter("acao");
	System.out.println(acao);
	startServiceAndRepository(request, response);
	setInformacoes(request, response);
	if (acao != null) {
	    if (acao.equals("listar")) {
		listarTodosProdutos(request, response);
	    }
	} else {
	    listarTodosProdutos(request, response);
	}
    }

    private void setInformacoes(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
	    request.setAttribute("gerais",
		    ProdutosUtil.mostrarInfosProdutos(this.cat.getUser(), service, this.cat.getOrcamento()));
	    request.setAttribute("disponivel", ProdutosUtil.disponivelParaComprar(service, cat));
	    request.setAttribute("economizado", ProdutosUtil.valorEconomizado(service));
	} catch (NullPointerException e) {
	    response.sendRedirect("categorias.jsp");
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	setInformacoes(request, response);
	if (acao != null) {
	    System.out.println(acao + " método POST");
	    startServiceAndRepository(request, response);
	    if (acao.equals("listar")) {
		listarTodosProdutos(request, response);
	    } else if (acao.equals("selecionar")) {
		String id = request.getParameter("id");
		Product p = repository.findById(Integer.parseInt(id));
		System.out.println(p);
	    } else if (acao.equals("salvar")) {
		salvarProduto(request, response);
	    } else if (acao.equals("editar")) {
		redirecionarEditarProduto(request, response);
	    } else if (acao.equals("excluir")) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		service.deleteById(id);
		response.sendRedirect("produtos");
	    } else if (acao.equals("comprados")) {
		listarComprados(request, response);
	    } else if (acao.equals("nao_comprados")) {
		listarNaoComprados(request, response);
	    }
	}
    }

    private void redirecionarEditarProduto(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String id = request.getParameter("id");
	Product p = repository.findById(Integer.parseInt(id));
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
	    System.out.println("E=" + valorEstipulado + "\nR=" + valorReal + "\nN=" + nome + "\nID=" + id);
	    System.out.println("C= " + comprado);
	    Product p = new Product(!id.isEmpty() ? Integer.parseInt(id) : null, nome, null, null, false, cat.getUser(),
		    cat);
	    p.setPrecoEstipulado(Double.parseDouble(parseNumber(valorEstipulado)));
	    p.setPrecoReal(!valorReal.isEmpty() ? Double.parseDouble(parseNumber(valorReal)) : 0.0);
	    p.setComprado(comprado == null || comprado.equals("false") ? false : true);
	    if (p.getId() == null) {
		service.inserir(p);
	    } else {
		Integer catId = Integer.parseInt(request.getParameter("cat_id"));
		Integer catOriginal = cat.getId();
		if (catOriginal != catId) {
		    // movendo a categoria
		    cat.setId(catId);
		}
		service.atualizar(p);// moveu

		// voltando pra categoria atual
		cat.setId(catOriginal);
	    }
	    response.sendRedirect("produtos");
	} catch (NumberFormatException e) {
	    request.setAttribute("error", "Digite um número válido");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("add-produto.jsp");
	    request.setAttribute("nome", nome);
	    dispatcher.forward(request, response);
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
	try {
	    request.getSession().setAttribute("produtos", service.findAll());
	} catch (ListaVaziaException e) {
	    request.setAttribute("error", e.getMessage());
	} finally {
	    dispatcher = request.getRequestDispatcher("produtos.jsp");
	    dispatcher.forward(request, response);
	}

    }

    private void listarComprados(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	try {
	    request.getSession().setAttribute("produtos", service.getProdutosConcluidos());
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
}
