package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
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
	response.setContentType("text/plain");
	response.setCharacterEncoding("UTF-8");
	try {
	    User user = repository.login(username, password);
	    if (user != null) {
		// invalidando a ultima sessão
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
		    oldSession.invalidate();
		}
		// generate a new session
		HttpSession newSession = request.getSession(true);
		newSession.setMaxInactiveInterval(30 * 60);
		newSession.setAttribute("user", user);
		Cookie id = new Cookie("b80bb7740288fda1f201890375a60c8f", user.getId().toString()); //md5 do nome id xD só pra nao deixar tão óbvio
		response.addCookie(id);
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("home");
	    }
	} catch (MyLoginException e) {
	    request.setAttribute("msg", e.getMessage());
	    request.setAttribute("username", username);
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    response.getWriter().write(e.getMessage());
	}
    }
}
