package com.onlywd.user.web.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = "/adminjsps/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        if (request.getSession().getAttribute("adminname") != null){
            chain.doFilter(req,resp);
        }else {
            if (servletPath.equals("/adminjsps/login.jsp")){
                chain.doFilter(req,resp);
            }else {
                response.sendRedirect(request.getContextPath()+"/adminjsps/login.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
