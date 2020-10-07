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
import com.ray.model.service.UserService;

@WebServlet("/my-account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao repository = null;
    private UserService service = null;

    public AccountServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String action = request.getParameter("acao");
	if (action != null) {
	    Long id = Long.valueOf(request.getParameter("id"));
	    String name = request.getParameter("nome");
	    String username = request.getParameter("username");
	    service = new UserService();
	    repository = DaoFactory.createUserDao();
	    if (action.equals("editar")) {
		try {
		    if (service.update(id, name, username)) {
			request.setAttribute("msg", "Editado com sucesso!");
			request.setAttribute("user", repository.findById(id));
		    }else {
			request.setAttribute("msg", "Ocorreu um erro inesperado");
		    }
		} catch (MyLoginException e) {
		    request.setAttribute("msg", "O username escolhido já está em uso. Tente usar outro");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	    }
	}
    }

}