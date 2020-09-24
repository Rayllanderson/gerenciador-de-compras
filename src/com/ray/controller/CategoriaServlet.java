package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.model.entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    private UserDao repository = DaoFactory.createUserDao();
    
    public CategoriaServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpServletRequest req = (HttpServletRequest) request; //convertendo o request 
	HttpSession session = req.getSession(); //pegando a seção
	User user = (User) session.getAttribute("user");
	System.out.println(user);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
    }

}
