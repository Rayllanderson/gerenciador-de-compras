package com.ray.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.ray.db.DB;

@WebFilter(urlPatterns = { "/*" }) // toda requisição vai passar pelo filter
public class DBFilter implements javax.servlet.Filter {
//    private HttpServletRequest req; // convertendo o request
//    private HttpSession session;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	try {
//	    req = (HttpServletRequest) request;
//	    session = req.getSession();
	    chain.doFilter(request, response);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	DB.getConnection();
    }

    @Override
    public void destroy() {
    }
    
    
}
