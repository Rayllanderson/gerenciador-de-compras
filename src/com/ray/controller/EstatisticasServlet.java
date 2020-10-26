package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.entities.User;
import com.ray.model.util.UserUtil;

@MultipartConfig
@WebServlet("/estatisticas")
public class EstatisticasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EstatisticasServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	listarTudo(request, response);
    }

    private void listarTudo(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	setInformacoes(request);
	response.setStatus(200);
	request.getRequestDispatcher("estatisticas.jsp").forward(request, response);
    }

    private void setInformacoes(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	UserUtil infos = new UserUtil(user);
	request.getSession().setAttribute("tListas", infos.getNumeroTotalCategorias());
	request.getSession().setAttribute("tProdutos", infos.getNumTotalProdutos());
	request.getSession().setAttribute("nProdutosComprados", infos.getNumTotalProdutosComprados());
	request.getSession().setAttribute("tEstip", infos.getTotalEstipulado());
	request.getSession().setAttribute("tGasto", infos.getTotalReal());
    }

  
}
