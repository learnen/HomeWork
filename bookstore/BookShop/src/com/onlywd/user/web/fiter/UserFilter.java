package com.onlywd.user.web.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter",urlPatterns = {"/jsps/*","/bank_img/*","/images/*","/menu/*"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();
        HttpServletResponse response = (HttpServletResponse) resp;
        Object user = request.getSession().getAttribute("user");
        if (user != null){
            chain.doFilter(req, resp);
        }else {
            if (servletPath.equals("/jsps/user/login.jsp") || servletPath.equals("/jsps/user/regist.jsp")){
                chain.doFilter(req, resp);
            }else {
                response.sendRedirect("/bookshop/jsps/user/login.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
