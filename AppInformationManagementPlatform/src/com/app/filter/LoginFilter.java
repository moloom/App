package com.app.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.app.pojo.Dev_user;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("-----------------------doFilter------------------");
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		ModelAndView model = new ModelAndView();
		// 获取上下文路径
		String path = servletRequest.getScheme() + "://" + servletRequest.getServerName() + ":"
				+ servletRequest.getServerPort() + servletRequest.getContextPath();
		System.out.println(path);
		// 获得用户请求的URI
		String uriPath = servletRequest.getServletPath();
		System.out.println(uriPath);
		// 从session里取用户
		Dev_user dev_user = (Dev_user) session.getAttribute("devUserSession");
		System.out.println("\n devUserSession :" + dev_user);
		// 如果是login , , modify、,"/user/logOut.html"放行
		String[] strs = { "/login", "/index.jsp" };
		for (String string : strs) {
			// string=servletRequest.getContextPath()+"/"+string;
			// string="/"+string;
			// System.out.println(string);
			if (uriPath.equals(string)) {
				chain.doFilter(request, response);
				return;
			}
		}

		// 判断如果没有取到用户信息,说明这个请求是没有登录就在请求 就跳转到登陆页面
		if (dev_user == null) {
			// 跳转到登陆页面
			// model.addObject("error", "您未登陆，自动为您跳转到登录界面");
			System.out.println("user=null");
			session.setAttribute("error", "您未登陆，自动为您跳转到登录界面");
			// request.setAttribute("msg", "您未登陆，自动为您跳转到登录界面");
			servletResponse.sendRedirect(path);
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}
		System.out.println("-----------------------doFilter-End-----------------");
		// pass the request along the filter chain
		// chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
