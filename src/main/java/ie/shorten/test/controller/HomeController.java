package ie.shorten.test.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;

@Controller
public class HomeController {
	
	ProductRepository product_repository;
	
	@RequestMapping("/")
	public String index(Locale locale, Model model){
		List<Product> product_list = product_repository.findAllproducts();
		model.addAttribute(product_list);
		return "index";
	}
	
}
