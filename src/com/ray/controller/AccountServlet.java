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
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;
import com.ray.model.util.UserUtil;

@WebServlet("/my-account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao repository = DaoFactory.createUserDao();
    private UserService service = new UserService();

    public AccountServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String action = request.getParameter("action");
	if (action != null) {
	    setInformacoes(request);
	    if (action.equals("view")) {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	    }
	}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String action = request.getParameter("action");
	if (action != null) {
	    Long id = Long.valueOf(request.getParameter("id"));
	    String name = request.getParameter("nome");
	    String username = request.getParameter("username");
	    if (action.equals("editar")) {
		if (!fieldsAreValids(name, username)) {
		    response.setStatus(400);
		    response.getWriter().write("Um ou mais campos estãos vazios");
		} else {
		    if (isModified(name, username, request)) {
			try {
			    if (service.update(id, name, username)) {
				response.setStatus(200);
				response.getWriter().write("Editado com sucesso!");
				request.getSession().setAttribute("user", repository.findById(id));
				setInformacoes(request);
			    } else {
				response.setStatus(500);
				response.getWriter().write("Ocorreu um erro inesperado");
			    }
			} catch (MyLoginException e) {
			    response.setStatus(409);
			    response.getWriter().write("O username escolhido já está em uso. Tente usar outro");
			}
		    } else {
			response.setStatus(200);
			response.getWriter().write("Nenhuma alteração foi detectada.");
		    }
		}
	    }
	}
    }

    /**
     * verifica se um dos campos estão vazios
     * 
     * @param name
     * @param username
     * @return true caso os campos sejam válidos
     */
    private boolean fieldsAreValids(String name, String username) {
	return !(name.isEmpty() || username.isEmpty());
    }

    private void setInformacoes(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	UserUtil util = new UserUtil(user);
	request.getSession().setAttribute("tListas", util.getNumeroTotalCategorias());
	request.getSession().setAttribute("tProdutos", util.getNumTotalProdutos());
	request.getSession().setAttribute("nProdutosComprados", util.getNumTotalProdutosComprados());
	request.getSession().setAttribute("tEstipulado", util.getTotalEstipulado());
	request.getSession().setAttribute("tGasto", util.getTotalValorReal());
    }

    /**
     * checa se nome e username do user foi alterado
     * 
     * @param nome    & username - para verificar com o user atual
     * @param request para capturar o user atual
     * @return true caso detecte que um dos campos foi modificado
     */
    private boolean isModified(String nome, String username, HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	return !(user.getName().equals(nome) && user.getUsername().equals(username));
    }
}