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
	  @RequestMapping("/register")
	  public String registrationPage() {
		  System.out.println("inside register page menthod");
		  return "registration";
	  }
	  @RequestMapping("/login")
	  public String loginPage() {
		  System.out.println("inside Login page menthod");
		  return "login";
	  }
	  
	  @RequestMapping("/dashboard")
	  public String dashboard() {
		  System.out.println("inside dashboard page menthod");
		  return "dashboard";
	  }
	  
	  @RequestMapping("/restaurants")
	  public String restaurant() {
		  System.out.println("inside dashboard page menthod");
		  return "restaurant";
	  }
	  
	  @RequestMapping("/restaurantsDetails")
	  public String restaurantDetail() {
		  System.out.println("inside dashboard page menthod");
		  return "restaurantDetail";
	  }
}
