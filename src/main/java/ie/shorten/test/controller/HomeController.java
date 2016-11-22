package ie.shorten.test.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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
