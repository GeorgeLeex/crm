package com.neuedu.system.tools;

import com.neuedu.system.db.DBUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;

import java.io.IOException;
/**
 * Connection对象过滤器, 在响应结束后关闭当前线程的Connection对象
 * @author Administrator
 *
 */
@WebFilter("/*")
public class ConnectionFilter extends HttpServlet implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
        }finally {
            DBUtils.Close();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
