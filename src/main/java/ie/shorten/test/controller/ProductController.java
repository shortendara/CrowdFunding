package ie.shorten.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepository product_repository;
	
	
	@RequestMapping("/product/{id}")
	public String product(Model model){
		//using id=1 as a test
		List<Product> product = product_repository.findByid(1);
		model.addAttribute("product_id", product);
		return "product";
	}

}
