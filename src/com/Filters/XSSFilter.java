package com.Filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/page/*")
public class XSSFilter implements Filter {

	public XSSFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Algorithm for XSS detection
		boolean flag = false;

		System.out.println("**********************************************");
		System.out.println("Starting XSS detection engine.....");

		List<String> attr = new ArrayList<>();

		HttpServletRequest req = (HttpServletRequest) request;

		Enumeration<?> e = request.getParameterNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			attr.add(name);

		}

		for (String a : attr) {
			String para = req.getParameter(a);

			if (clientInputFilter(para)) {
				flag = true;

			}

		}

		if (flag) {
			System.out.println("Web Request contains possible XSS attack intention");
			request.getRequestDispatcher("/page/error.html").forward(request, response);
		} else {
			System.out.println("Request is secure. sending for next filter..");
			chain.doFilter(request, response);
			System.out.println("**********************************************");
		}

	}

	boolean clientInputFilter(String arg) {
		String pattern = "^[a-z0-9 \\s @ . - ! ^ + $ ~ { } ? \\' \\\" |  :  * ( ) ^ , = ` ]+$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(arg);
		return !m.find();

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
