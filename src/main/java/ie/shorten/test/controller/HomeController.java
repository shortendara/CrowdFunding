package ie.shorten.test.controller;


import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepository product_repository;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/allProducts")
	public String index(Model model){
		List<Product> product_list = product_repository.findAll();
		model.addAttribute(product_list);
		return "allProducts";
	}
	
}
