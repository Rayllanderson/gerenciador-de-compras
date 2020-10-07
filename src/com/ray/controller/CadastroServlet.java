package com.ray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService service = new UserService();

    public CadastroServlet() {
	super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String name = request.getParameter("nome");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String password2 = request.getParameter("password2");

	if (validarCampos(request, response, name, username, password, password2)) {  
	    try {
		User user = new User(null, name, username, password);
		service.cadastrar(user);
		request.setAttribute("msg", "Cadastro realizado com sucesso! Faça login para continuar");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	    } catch (MyLoginException e) { //username ja em uso
		request.setAttribute("msg", e.getMessage());
		request.setAttribute("nome", name);
		request.setAttribute("username", username);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		dispatcher.forward(request, response);
	    }
	}else {
	    request.setAttribute("nome", name);
	    request.setAttribute("username", username);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
	    dispatcher.forward(request, response);
	}
    }

    /**
     * @return true caso os campos estejam válidos
     */
    private boolean validarCampos(HttpServletRequest request, HttpServletResponse response, String name,
	    String username, String password, String password2) throws ServletException, IOException {
	if(name.isEmpty()) {
	    name = "Convidado";
	}
	if (username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
	    request.setAttribute("msg", "Um ou mais campos estão vazios");
	    return false;
	} else if (!password.equals(password2)) {
	    request.setAttribute("msg", "As senhas não correspodem");
	    return false;
	}
	return true;
    }
}
