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
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.CategoriaService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriaDao repository;
    private CategoriaService service;
    private boolean flag; // para permitir pesquisar nulo e listar todas as categorias, mas isso apenas
			  // uma vez

    public CategoriaServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	User user = instanciarUser(request);
	repository = DaoFactory.createCategoriaDao(user);
	service = new CategoriaService(user);
	if (acao != null) {
	    if (acao.equals("voltar") || acao.equals("listar")) {
		listarTodasCategorias(request, response);
	    } else if (acao.equals("newList")) {
		response.sendRedirect("add-categoria.jsp");
	    } else if (acao.equals("excluir")) {
		deletarCategoria(request, response);
	    } else if (acao.equals("search")) {
		search(request, response);
	    } else {
		listarTodasCategorias(request, response);
	    }
	} else {
	    listarTodasCategorias(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    User user = instanciarUser(request);
	    repository = DaoFactory.createCategoriaDao(user);
	    service = new CategoriaService(user);
	    System.out.println(acao);
	    if (acao.equals("listar")) {
		listarTodasCategorias(request, response);
	    } else if (acao.equals("selecionar")) {
		selecionarLista(request, response);
	    } else if (acao.equals("salvar")) {
		salvarLista(request, response, user);
	    } else if (acao.equals("editar")) {
		redirecionarEditPage(request, response);
	    }
	}
    }

    private void deletarCategoria(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	String id = request.getParameter("id1");
	System.out.println("cara... id chegou aqui foi esse..: " + id);
	if (service.deleteById(Long.parseLong(id))) {
	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	    request.getRequestDispatcher("categorias.jsp").forward(request, response);
	} else {
	    response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
	}

    }

    private void redirecionarEditPage(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String id = request.getParameter("id");
	Categoria cat = repository.findById(Long.parseLong(id));
	request.getSession().setAttribute("cat", cat);
	RequestDispatcher dispatcher = request.getRequestDispatcher("edit-categoria.jsp");
	dispatcher.forward(request, response);
    }

    /**
     * 
     * Será a lista selecionada, então redirecionará para os produtos dessa lista
     * selecionada
     */
    private void selecionarLista(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String id = request.getParameter("id");
	Categoria cat = repository.findById(Long.parseLong(id));
	request.getSession().setAttribute("categoria", cat);
	response.sendRedirect("produtos?acao=listar");
    }

    private void salvarLista(HttpServletRequest request, HttpServletResponse response, User user)
	    throws IOException, ServletException {
	String id = request.getParameter("id");
	String nome = request.getParameter("nome");
	String orcamento = request.getParameter("orcamento");
	System.out.println("orcamento= " + orcamento);
	System.out.println("nome " + nome);
	System.out.println("id = " + id);
	Categoria cat = new Categoria(!id.isEmpty() ? Long.parseLong(id) : null, nome, user);
	cat.setOrcamento(!orcamento.isEmpty() ? Double.valueOf(parseNumber(orcamento)) : 0.0);
	try {
	    if (cat.getId() == null) {
		cat = service.salvar(cat);
		response.setStatus(HttpServletResponse.SC_CREATED);
//	    String json = new Gson().toJson(cat);
//	    System.out.println(json);
//	    response.setContentType("application/json");
//	    response.getWriter().write(json);
	    } else {
		service.update(cat);
		response.setStatus(HttpServletResponse.SC_OK);
	    }
	    request.getRequestDispatcher("categorias.jsp").forward(request, response);
	} catch (CategoriaException e) {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    response.getWriter().println(e.getMessage());
	}

    }

    private void listarTodasCategorias(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = null;
	request.getSession().setAttribute("categorias", repository.findAll());
	dispatcher = request.getRequestDispatcher("categorias.jsp");
	dispatcher.forward(request, response);
    }

    private User instanciarUser(HttpServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request; // convertendo o request
	HttpSession session = req.getSession(); // pegando a seção
	User user = (User) session.getAttribute("user");
	return user;
    }

    private String parseNumber(String value) {
	String valorParse = value.replaceAll("\\.", "");// retirando os pontos por nada
	return valorParse.replaceAll("\\,", "."); // agora só sobra a virgula, da só mudar pra .
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String serch = request.getParameter("search");
	try {
	    if (!serch.isEmpty()) {
		response.setStatus(HttpServletResponse.SC_OK);
		request.getSession().setAttribute("categorias", service.getCategoriaByName(serch));
		flag = true; // pode pesquisar com campo nulo que vai listar todas as categorias
	    } else if (flag) {
		listarTodasCategorias(request, response);
		System.out.println("entrou aqui haha");
		flag = false; // desativando a funcao para evitar listar sem necessidade
	    }
	} catch (ListaVaziaException e) {
	    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(e.getMessage());
	}
    }

}
