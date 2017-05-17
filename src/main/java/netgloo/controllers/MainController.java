package netgloo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	//Annotation for mapping
	// web requests onto specific handler classes and/or handler methods. 
  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "Welcome to the User Info Page " + 
        " - Please enter your information:";
  }

}
