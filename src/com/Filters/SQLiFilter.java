package com.Filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.scsu.ia.Utility;

@WebFilter("/page/*")
public class SQLiFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		System.out.println("**********************************************");
		System.out.println("Starting SQLi detection engine.....");
		boolean flag = false;
		List<String> attr=new ArrayList<>();
		
		
		//Algorithm for SQLi detection
		/* For case study purpose, implementation contains trivial keyword matching however
		 * it is not reliable solution as there are ways to bypass keyword matching. 
		 * Purpose of this study is the design. In future, reliable SQLi detection algorithm should be 
		 * plugged in the design
		 */
		
		
		
		HttpServletRequest req = (HttpServletRequest) arg0;
		
		
		String uri=req.getQueryString()==null?req.getRequestURI().toString():req.getRequestURI().toString()+"?"+req.getQueryString().toString();
		System.out.println("URI: "+uri);
		if(uri.toLowerCase().contains("union")||uri.toLowerCase().contains("select")||uri.toLowerCase().contains("--")||uri.toLowerCase().contains("'")||uri.toLowerCase().contains("where")||uri.toLowerCase().contains("from")||uri.toLowerCase().contains("insert")||uri.toLowerCase().contains("delete")||uri.toLowerCase().contains("update")||uri.toLowerCase().contains("drop")||uri.toLowerCase().contains("truncate")||uri.toLowerCase().contains("alter")||uri.toLowerCase().contains("from")||uri.toLowerCase().contains("join"))
		{
			System.out.println("URI contains possible SQLi attack");
			arg0.getRequestDispatcher("/page/error.html").forward(arg0, arg1);
			
			
			
		}
		
		else
		{	
			Enumeration<?> e = arg0.getParameterNames();
			
			while (e.hasMoreElements())
			{
			    String name = (String) e.nextElement();
			    attr.add(name);
			    //System.out.println(name);
			   
			    
			}
			
			for(String a: attr)
			{	String para=req.getParameter(a);
				
				System.out.println("Parameter:"+para);
				if(para.toLowerCase().contains("union")||para.toLowerCase().contains("select")||para.toLowerCase().contains("  or ")||para.toLowerCase().contains("'")||para.toLowerCase().contains("where")||para.toLowerCase().contains("from")||para.toLowerCase().contains("insert")||para.toLowerCase().contains("delete")||para.toLowerCase().contains("update")||para.toLowerCase().contains("drop")||para.toLowerCase().contains("truncate")||para.toLowerCase().contains("alter")||para.toLowerCase().contains("from")||para.toLowerCase().contains("join"))
				{
					flag= true;
					
					
					
				}
							
				
			}
			
			if(flag)
			{
				System.out.println("Web Request contains possible SQLi attack intention");
				arg0.getRequestDispatcher("/page/error.html").forward(arg0, arg1);
			}
			else
			{	System.out.println("Request is secure. sending for next filter..");
				arg2.doFilter(arg0, arg1);
				System.out.println("**********************************************");
			}
			
			
		}
		
		
		
		
		
		
		/*
		 * if(flag) { arg0.getRequestDispatcher("/page/error.html").forward(arg0, arg1);
		 * 
		 * 
		 * } else { arg2.doFilter(arg0, arg1);
		 * 
		 * }
		 */
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
