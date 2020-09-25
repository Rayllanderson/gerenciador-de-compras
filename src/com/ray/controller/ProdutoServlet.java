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

/**
 * Servlet implementation class Login
 */
@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDao repository = null;

    public ProdutoServlet() {
	super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	System.out.println("método GET");
	iniciarRepository(request, response);
	listarTodosProdutos(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    System.out.println(acao + " método POST");
	    iniciarRepository(request, response);
	    if (acao.equals("listar")) {
		listarTodosProdutos(request, response);
	    } else if (acao.equals("selecionar")) {
		String id = request.getParameter("id");
		Product p = repository.findById(Integer.parseInt(id));
		System.out.println(p);
	    }
	}
    }

    private void iniciarRepository(HttpServletRequest request, HttpServletResponse response) {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	Categoria cat = (Categoria) session.getAttribute("categoria");
	repository = DaoFactory.createProductDao(cat);
    }

    private void listarTodosProdutos(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("produtos", repository.findAll());
	dispatcher = request.getRequestDispatcher("produtos.jsp");
	dispatcher.forward(request, response);
    }
}
