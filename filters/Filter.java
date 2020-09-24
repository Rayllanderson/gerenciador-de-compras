package com.ray.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.ray.db.DB;
/*essa classe conecta o banco de dados, i guess*/
@WebFilter(urlPatterns = { "/*" }) // toda requisição vai passar pelo filter
public class Filter implements javax.servlet.Filter {

    private static Connection conn = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	try {
	    chain.doFilter(request, response);
	    conn.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	conn = DB.getConnection();
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
	
    }
}
