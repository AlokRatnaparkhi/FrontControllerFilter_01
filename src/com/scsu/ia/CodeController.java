package com.scsu.ia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CodeController {
	
	@RequestMapping("/code.html")
	String getCodePage()
	{		System.out.println("Inside code controller");
		return "code";
	}
	
	
}
