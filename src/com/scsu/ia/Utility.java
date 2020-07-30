package com.scsu.ia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	
	
	
	 public static boolean verifySQLiPattern(String a)
	{	System.out.println("Inside Regex");
		boolean flag=false;
		
	    String pattern = "(select|union|'|having|where|from|alter|drop|truncate|delete|insert|or|and|join)";
		
	    Pattern r = Pattern.compile(pattern);
	     
	    Matcher m = r.matcher(a);
		flag= m.matches()?true:false;
		System.out.println("Flag: "+flag);
		return flag;
	}
	
	
	
	
	
	
}
