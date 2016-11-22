package ie.shorten.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ie.shorten.test.service.UserService;

@Controller
@RequestMapping("/app")
public class UserController {
	
	@Autowired
        private UserService service;
	
	@RequestMapping(value="/login")
	public String homePage() {
 		return "customLogin";
 	}    
	
	@RequestMapping(value="/secure/UserDetail")
	public String UserDetail(Model model) {
		model.addAttribute("Users", service.getUsers());
 		return "User";
 	}
}
