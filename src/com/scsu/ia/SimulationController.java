package com.scsu.ia;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

@Controller
public class SimulationController {
	
	
	@RequestMapping("/simulation.html")
	String getSimulation()
	{	System.out.println("Inside simulation controller");
		
		return "simulation";
	}
	
	@RequestMapping(value="/UserInformation",method=RequestMethod.POST)
	void getUserInfo(HttpServletRequest request,HttpServletResponse response ) throws IOException
	{
		
	String userId = request.getParameter("userName");
	String userPass = request.getParameter("userPass");
	String protection = request.getParameter("protection");
	 
	System.out.println("Username of the application is:"+userId);
	System.out.println("Password of the application is:"+userPass);
	
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "-1");

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    response.setHeader("Access-Control-Max-Age", "86400");

    JsonObject myObj = null;
    if("without".equals(protection)){
    	myObj = new DBManager().getInfo(userId,userPass);
    }else{
    	myObj = new DBManager().getInfoProtection(userId,userPass);
    }
    System.out.println(myObj.toString());
    out.println(myObj.toString());
    System.out.println("end of the Servlet!");
    out.close();
		
	}
	
	
}
