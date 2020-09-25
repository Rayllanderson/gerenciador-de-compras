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
	String id = request.getParameter("id");
	System.out.println(id);
	if(acao != null) {
	    System.out.println(acao);
	    if(acao.equals("listar")) {
		HttpServletRequest req = (HttpServletRequest) request; //convertendo o request 
		HttpSession session = req.getSession(); //pegando a seção
		User user = (User) session.getAttribute("user");
		repository= DaoFactory.createCategoriaDao(user);
		request.getSession().setAttribute("categorias", repository.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("categorias.jsp");
		dispatcher.forward(request, response);
	    }
	    
	}
    }
}
