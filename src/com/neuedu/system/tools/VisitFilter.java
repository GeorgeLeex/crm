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
 * 访问过滤器
 * @author Administrator
 *
 */
@WebFilter("*.do")
public class VisitFilter implements Filter {
    public VisitFilter() {
        // TODO Auto-generated constructor stub
    }
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
    	HttpServletResponse response = (HttpServletResponse) resp;
    	//获取session域中存在userInfoMap这个用户信息集合
    	Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("userInfoMap");
    	//如果请求路径为登录,放行
    	if (request.getRequestURL().toString().contains("lx_UserLogin.do")){
    		chain.doFilter(req, resp);
    		//如果没有该集合,则跳转至登录页面
       } else if (map == null) {
    	   request.getRequestDispatcher("/page/login2.jsp").forward(request, response);
       } else {
    	   chain.doFilter(req, resp);
       }
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
