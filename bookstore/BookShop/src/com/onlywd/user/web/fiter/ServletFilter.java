package com.onlywd.user.web.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter",
        urlPatterns = {"/email","/user","/order","/category","/book"
                ,"/adminUser","/adminBook","/adminCategory","/adminOrder"})
public class ServletFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String referer = request.getHeader("Referer");
        if (referer != null){
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("/bookshop/jsps/user/login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
