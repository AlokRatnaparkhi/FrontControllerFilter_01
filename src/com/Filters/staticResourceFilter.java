package com.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class staticResourceFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String CONTEXT_PATH="/IA683HW1/page";
		System.out.println("Inside Resource Filter");
		HttpServletRequest req = (HttpServletRequest) arg0;
		String path = req.getRequestURI().substring(CONTEXT_PATH.length());

		if (path.startsWith("/resources/")) {
		     // Goes to default servlet.
			arg0.getRequestDispatcher(path).forward(arg0, arg1);
		} 
		else
		{	System.out.println("Inside Resource Filter else");
			
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
