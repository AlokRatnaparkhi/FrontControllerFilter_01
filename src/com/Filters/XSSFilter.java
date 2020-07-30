package com.Filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class XSSFilter
 */
@WebFilter("/page/*")
public class XSSFilter implements Filter {

    /**
     * Default constructor. 
     */
    public XSSFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		//Algorithm for XSS detection
				/* For case study purpose, implementation contains trivial keyword matching however
				 * it is not reliable solution as there are ways to bypass keyword matching. 
				 * Purpose of this study is the design. In future, reliable XSS detection algorithm should be 
				 * plugged in the design
				 */
		
		// pass the request along the filter chain
		boolean flag = false;
		
		
		System.out.println("**********************************************");
		System.out.println("Starting XSS detection engine.....");
		
		List<String> attr=new ArrayList<>();
				
		HttpServletRequest req = (HttpServletRequest) request;
				
		Enumeration<?> e = request.getParameterNames();
			
			while (e.hasMoreElements())
			{
			    String name = (String) e.nextElement();
			    attr.add(name);
			    //System.out.println(name);
			   
			    
			}
			
			for(String a: attr)
			{	String para=req.getParameter(a);
				
				System.out.println("Parameter:"+para);
				if(para.toLowerCase().contains("<")||para.toLowerCase().contains(">")||para.toLowerCase().contains("%")||para.toLowerCase().contains("script")||para.toLowerCase().contains("document")||para.toLowerCase().contains("/")||para.toLowerCase().contains("session")||para.toLowerCase().contains("inner")||para.toLowerCase().contains("html")||para.toLowerCase().contains("alert")||para.toLowerCase().contains("body")||para.toLowerCase().contains("session")||para.toLowerCase().contains(".")||para.toLowerCase().contains("(")||para.toLowerCase().contains(")"))
				{
					flag= true;
					
					
					
				}
							
				
			}
			
			if(flag)
			{
				System.out.println("Web Request contains possible XSS attack intention");
				request.getRequestDispatcher("/page/error.html").forward(request, response);
			}
			else
			{	System.out.println("Request is secure. sending for next filter..");
				chain.doFilter(request, response);
				System.out.println("**********************************************");
			}
			
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
