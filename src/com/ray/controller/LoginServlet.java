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
@WebServlet("/home")
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
	try {
	    User user = repository.login(username, password);
	    if (user != null) {
//	    session.setAttribute("user", repository.login(username, password)); //logando. passou daqui, logou!
//	    session.setMaxInactiveInterval(30*60); //30 minutos de inatividade
	    
	    //invalidando a ultima sessão
	    HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            
            //generate a new session
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(30*60);
            
            newSession.setAttribute("user", user);
            
            Cookie message = new Cookie("message", "Welcome");
            response.addCookie(message);
            response.sendRedirect("home.jsp");    
	    }
	} catch (MyLoginException e) {
	    request.setAttribute("error", e.getMessage());
	    request.setAttribute("username", username);
	    response.sendRedirect("home.jsp");
	}
    }
}

