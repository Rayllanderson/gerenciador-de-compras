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

/**
 * Servlet implementation class Login
 */
@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   private ProductDao repository;
    
    public ProdutoServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpServletRequest req = (HttpServletRequest) request; //convertendo o request 
	HttpSession session = req.getSession(); //pegando a seção
	Categoria cat = (Categoria)session.getAttribute("categoria");
	repository = DaoFactory.createProductDao(cat);
	request.getSession().setAttribute("produtos", repository.findAll());
	RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpServletRequest req = (HttpServletRequest) request; //convertendo o request 
	HttpSession session = req.getSession(); //pegando a seção
	Categoria cat = (Categoria)session.getAttribute("categoria");
	repository = DaoFactory.createProductDao(cat);
	request.getSession().setAttribute("produtos", repository.findAll());
	RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
	dispatcher.forward(request, response);
    }
}
