package com.neuedu.system.tools;

import java.io.IOException;
import java.util.Map;

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
 * 编码过滤器
 * @author Administrator
 *
 */
@WebFilter("*")
public class CodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	chain.doFilter(req, resp);
        resp.setCharacterEncoding("UTF-8");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
