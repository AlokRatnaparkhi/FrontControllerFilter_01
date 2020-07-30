package com.scsu.ia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArchitectureController {

	@RequestMapping("/architecture.html")
	String getArchitecturePage()
	{		System.out.println("Inside architecture controller");
		
		return "architecture";
	}
	
	
	
	
}
