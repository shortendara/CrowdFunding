package ie.shorten.test.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.shorten.test.repository.ProductRepository;

@Controller
public class HomeController {
	
	ProductRepository product_repository;
	
	@RequestMapping("/")
	public String home(Locale locale, Model model){
		return "home";
	}
	
}
