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
	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if(acao != null) {
	    System.out.println(acao);
	    RequestDispatcher dispatcher = null;
	    if(acao.equals("listar")) {
		HttpServletRequest req = (HttpServletRequest) request; //convertendo o request 
		HttpSession session = req.getSession(); //pegando a se��o
		User user = (User) session.getAttribute("user");
		repository= DaoFactory.createCategoriaDao(user);
		request.getSession().setAttribute("categorias", repository.findAll());
		dispatcher = request.getRequestDispatcher("categorias.jsp");
	    }else if(acao.equals("selecionar")) {
		String id = request.getParameter("id");
		Categoria cat = repository.findById(Integer.parseInt(id));
		request.getSession().setAttribute("categoria", cat);
		dispatcher = request.getRequestDispatcher("produtos?acao=listar");
	    }
	    dispatcher.forward(request, response);
	}
    }
}