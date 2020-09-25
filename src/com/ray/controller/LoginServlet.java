package com.ray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.exception.MyLoginException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao repository = DaoFactory.createUserDao();

    public LoginServlet() {
	super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	RequestDispatcher dispatcher = null;
	try {
	    request.getSession().setAttribute("user", repository.login(username, password));
	    dispatcher = request.getRequestDispatcher("home.jsp");
	} catch (MyLoginException e) {
	    request.setAttribute("error", e.getMessage());
	    dispatcher = request.getRequestDispatcher("index.jsp");
	    request.setAttribute("username", username);
	} finally {
	    dispatcher.forward(request, response);
	}
    }
}
