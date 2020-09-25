package com.ray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriaDao repository;

    public CategoriaServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    User user = instanciarUser(request);
	    repository = DaoFactory.createCategoriaDao(user);
	    System.out.println(acao);
	    if (acao.equals("voltar") || acao.equals("listar")) {
		listarTodasCategorias(request, response);
	    } else if (acao.equals("newList")) {
		response.sendRedirect("add-categoria.jsp");
	    }
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    User user = instanciarUser(request);
	    repository = DaoFactory.createCategoriaDao(user);
	    System.out.println(acao);
	    if (acao.equals("listar")) {
		listarTodasCategorias(request, response);
	    } else if (acao.equals("selecionar")) {
		selecionarLista(request, response);
	    } else if (acao.equals("saveList")) {
		salvarLista(request, response, user);
	    }
	}
    }

    /**
     * 
	Será a lista selecionada, então redirecionará para os produtos dessa lista selecionada
     */
    private void selecionarLista(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String id = request.getParameter("id");
	Categoria cat = repository.findById(Integer.parseInt(id));
	request.getSession().setAttribute("categoria", cat);
	response.sendRedirect("produtos?acao=listar");
    }

    private void salvarLista(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
	String nome = request.getParameter("nomeLista");
	String orcamento = request.getParameter("orcamento");
	Categoria cat = new Categoria(null, nome, user);
	cat.setOrcamento(Double.valueOf(orcamento));
	repository.inserir(cat);
	response.sendRedirect("categorias?acao=listar");
    }

    private void listarTodasCategorias(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("categorias", repository.findAll());
	dispatcher = request.getRequestDispatcher("categorias.jsp");
	dispatcher.forward(request, response);
    }

    private User instanciarUser(HttpServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request; // convertendo o request
	HttpSession session = req.getSession(); // pegando a seção
	User user = (User) session.getAttribute("user");
	return user;
    }
}
