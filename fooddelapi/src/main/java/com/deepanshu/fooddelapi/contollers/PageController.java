package com.deepanshu.fooddelapi.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

	
	  @RequestMapping("/home")
	    public String homePage() {
		  System.out.println("inside the homPage method ");
	        return "index"; // loads templates/index.html
	    }
}
