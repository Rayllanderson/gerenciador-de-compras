package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Login
 */

@WebServlet("/themes")
public class ThemeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ThemeServlet() {
	super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String theme = request.getParameter("colorTheme");
	System.out.println(theme);
    }
}