package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.entities.User;
import com.ray.model.util.TotalProdutos;

@MultipartConfig
@WebServlet("/estatisticas")
public class EstatisticasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EstatisticasServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarTudo(request, response);
    }

    private void listarTudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setInformacoes(request);
        response.setStatus(200);
        request.getRequestDispatcher("estatisticas.jsp").forward(request, response);
    }

    private void setInformacoes(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        TotalProdutos infos = new TotalProdutos(user);
        request.getSession().setAttribute("totalListas", infos.getNumCategorias());
        request.getSession().setAttribute("totalProdutos", infos.getNumProdutos());
        request.getSession().setAttribute("numProdutosComprados", infos.getNumProdutosComprados());
        request.getSession().setAttribute("valorGastoComprados", infos.getGastoComprados());
        request.getSession().setAttribute("valorEstipComprados", infos.getEstipuladoComprados());
        request.getSession().setAttribute("valorGasto", infos.getValorGasto());
        request.getSession().setAttribute("valorEstip", infos.getEstipulado());
        request.getSession().setAttribute("economizado", infos.getEconomizado());
        request.getSession().setAttribute("restante", infos.getRestante());
        request.getSession().setAttribute("total", infos.getTotal());
    }


}
