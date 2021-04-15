package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.model.entities.User;
import com.ray.model.service.UserService;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String theme = request.getParameter("colorTheme");
        request.setAttribute("theme", theme);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theme = request.getParameter("colorTheme");
        User user = (User) request.getSession().getAttribute("user");
        UserService service = new UserService();
        service.changeTheme(user, theme);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}