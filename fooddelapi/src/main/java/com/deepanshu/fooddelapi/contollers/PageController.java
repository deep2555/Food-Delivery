package com.deepanshu.fooddelapi.contollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
	public static final Logger customLogger = LoggerFactory.getLogger(PageController.class);

	

	  @RequestMapping("/home")
	    public String homePage() {
		  customLogger.debug("inside the home page method");
	        return "index"; // loads templates/index.html
	    }
	  @RequestMapping("/register")
	  public String registrationPage() {
		  customLogger.debug("inside register page menthod");
		  return "registration";
	  }
	  @RequestMapping("/login")
	  public String loginPage() {
		  customLogger.debug("inside Login page menthod");
		  return "login";
	  }
	  
	  @RequestMapping("/dashboard")
	  public String dashboard() {
		  customLogger.debug("inside dashboard page menthod");
		  return "dashboard";
	  }
	  
	  @RequestMapping("/restaurants")
	  public String restaurant() {
		  customLogger.debug("inside dashboard page menthod");
		  return "restaurant";
	  }
	  
	  @RequestMapping("/restaurantsDetails")
	  public String restaurantDetail() {
		  customLogger.debug("inside dashboard page menthod");
		  return "restaurantDetail";
	  }
}
