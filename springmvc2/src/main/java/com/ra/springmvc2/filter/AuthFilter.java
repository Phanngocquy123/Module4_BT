package com.ra.springmvc2.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String url = req.getContextPath();
        String url1 = req.getRequestURI();
        String url2 = req.getServletPath();
//        if (url1.contains("/admin/")) {
        if (session.getAttribute("user") == null) {
            res.sendRedirect("/login"); // nếu chưa đăng nhập,chuyển về trang login
        } else {
            chain.doFilter(request, response); // đăng nhập rồi thì đi tiếp
        }
//        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
