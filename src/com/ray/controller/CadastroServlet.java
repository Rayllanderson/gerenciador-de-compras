package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;
import com.ray.model.util.Theme;
import com.ray.model.validacoes.UserValidation;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService service = new UserService();

    public CadastroServlet() {
	super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    String name = request.getParameter("nome");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String password2 = request.getParameter("password2");
	    boolean passwordIsValid = UserValidation.passwordIsValid(password, password2);
	    boolean fieldsAreNotEmpty = UserValidation.fieldsAreNotEmpty(username, password, password2);
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    if (fieldsAreNotEmpty) {
		if (passwordIsValid) {
		    try {
			name = name == null || name.isEmpty() ? "Convidado" : name;
			User user = new User(null, name, username, password, null, null, Theme.DEFAULT);
			service.cadastrar(user);
			setResponse(response, HttpServletResponse.SC_OK,
				"Cadastro realizado com sucesso! Faça login para continuar");
		    } catch (MyLoginException e) { // username ja em uso
			setResponse(response, HttpServletResponse.SC_CONFLICT, e.getMessage());
		    }
		} else {
		    setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "As senhas não correspodem");
		}
	    } else {
		setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Um ou mais campos estão vazios");
	    }
	} catch (Exception e) {
	    setResponse(response, HttpServletResponse.SC_BAD_GATEWAY, "Ocorreu um erro inesperado x_x");
	    e.printStackTrace();
	}
    }

    private void setResponse(HttpServletResponse response, int codigo, String msg) throws IOException {
	response.setStatus(codigo);
	response.getWriter().write(msg);
    }
}
