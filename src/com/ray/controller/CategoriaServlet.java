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
import com.ray.model.service.CategoriaService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriaDao repository;
    private CategoriaService service;

    public CategoriaServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	System.out.println(acao);
	if (acao != null) {
	    User user = instanciarUser(request);
	    repository = DaoFactory.createCategoriaDao(user);
	    service = new CategoriaService(user);
	    System.out.println(acao);
	    if (acao.equals("voltar") || acao.equals("listar")) {
		listarTodasCategorias(request, response);
	    } else if (acao.equals("newList")) {
		response.sendRedirect("add-categoria.jsp");
	    }
	}else {
	    User user = instanciarUser(request);
	    repository = DaoFactory.createCategoriaDao(user);
	    service = new CategoriaService(user);
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
	    }else if (acao.equals("excluir")) {
		deletarCategoria(request, response);
	    }
	}
    }

    private void deletarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String id = request.getParameter("id");
	service.deleteById(Integer.parseInt(id));
	response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	response.sendRedirect("categorias?acao=listar");
    }

    private void redirecionarEditPage(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String id = request.getParameter("id");
	Categoria cat = repository.findById(Integer.parseInt(id));
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
	Categoria cat = repository.findById(Integer.parseInt(id));
	request.getSession().setAttribute("categoria", cat);
	response.sendRedirect("produtos?acao=listar");
    }

    private void salvarLista(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
	String id = request.getParameter("id");
	String nome = request.getParameter("nomeLista");
	String orcamento = request.getParameter("orcamento");
	System.out.println("orcamento= " +orcamento);
	System.out.println("nome "+nome);
	System.out.println("id = " +id);
	Categoria cat = new Categoria(!(id.isEmpty()) ? Integer.parseInt(id) : null, nome, user);
	cat.setOrcamento(!orcamento.isEmpty() ? Double.valueOf(parseNumber(orcamento)) : 0.0);
	if (cat.getId() == null) {
	    service.salvar(cat);
	    response.setStatus(HttpServletResponse.SC_CREATED);
	} else {
	    service.update(cat);
	    response.setStatus(HttpServletResponse.SC_OK);
	}
	response.sendRedirect("categorias?acao=listar");
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
   	return valorParse.replaceAll("\\,", "."); //agora só sobra a virgula, da só mudar pra .
       }
}
