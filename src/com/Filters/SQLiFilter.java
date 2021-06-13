package com.Filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
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
public class SQLiFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		// Algorithm for SQLi detection

		System.out.println("**********************************************");
		System.out.println("Starting SQLi detection engine.....");
		boolean flag = false;
		List<String> attr = new ArrayList<>();

		HttpServletRequest req = (HttpServletRequest) arg0;
		Enumeration<?> e = arg0.getParameterNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			attr.add(name);

		}

		for (String a : attr) {
			String para = req.getParameter(a);

			if (clientInputFilter(para)) {
				System.out.println("Parameter:" + para);
				flag = true;

			}

		}

		if (flag) {
			System.out.println("Web Request contains possible SQLi attack intention");
			arg0.getRequestDispatcher("/page/error.html").forward(arg0, arg1);
		} else {
			System.out.println("Request is secure. sending for next filter..");
			arg2.doFilter(arg0, arg1);
			System.out.println("**********************************************");
		}

	}

	boolean clientInputFilter(String arg) {
		String pattern = "^[a-z0-9 \\s @ . -]+$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(arg);
		return !m.find();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
