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
import com.ray.model.service.ProductService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDao repository = null;
    private ProductService service = null;

    public ProdutoServlet() {
	super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	System.out.println("método GET");
	String acao = request.getParameter("acao");
	if (acao != null) {
	    startServiceAndRepository(request, response);
	    if(acao.equals("listar")) {
		listarTodosProdutos(request, response);
	    }
	}else {
	    listarTodosProdutos(request, response);
	}
	
	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
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
	    }else if(acao.equals("editar")) {
		redirecionarEditarProduto(request, response);
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
	    Categoria cat = instanciarCategoria(request);
	    Product p = new Product(id != null ? Integer.parseInt(id) : null, nome, null, null, false, cat.getUser(),
		    cat);
	    p.setPrecoEstipulado(Double.parseDouble(valorEstipulado));
	    p.setPrecoReal(!valorReal.isEmpty() ? Double.parseDouble(valorReal) : 0.0);
	    p.setComprado(comprado != null && comprado.equals("on") ? true : false);
	    if (p.getId() == null) {
		service.inserir(p);
	    } else {
		service.atualizar(p);
	    }
	    response.sendRedirect("produtos");
	} catch (NumberFormatException e) {
	    request.setAttribute("error", "Digite um número válido");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("add-produto.jsp");
	    request.setAttribute("nome", nome);
	    dispatcher.forward(request, response);
	}
    }

    private void startServiceAndRepository(HttpServletRequest request, HttpServletResponse response) {
	Categoria cat = instanciarCategoria(request);
	service = new ProductService(cat);
	repository = DaoFactory.createProductDao(cat);
    }

    private Categoria instanciarCategoria(HttpServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	Categoria cat = (Categoria) session.getAttribute("categoria");
	return cat;
    }

    private void listarTodosProdutos(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("produtos", repository.findAll());
	dispatcher = request.getRequestDispatcher("produtos.jsp");
	dispatcher.forward(request, response);
    }
}
