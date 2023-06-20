/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package br.edu.iftm.jsf.filter;

import br.edu.iftm.jsf.entity.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danilo
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Usuario usuario = (Usuario) httpRequest.getSession().getAttribute("usuarioLogado");;
        if (redireciona(httpRequest, usuario)) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/erro/login.xhtml");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        chain.doFilter(request, response);
    }

    public boolean redireciona(HttpServletRequest httpRequest, Usuario usuario) {
        if (httpRequest.getRequestURI().endsWith("login.xhtml")) {
            return false;
        }
        if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/javax.faces.resource")) {
            return false;
        }
        if(usuario != null) {
            return false;
        }
        return true;
    }

    
    
}
