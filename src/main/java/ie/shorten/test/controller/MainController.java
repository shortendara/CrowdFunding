package ie.shorten.test.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;
 
@Configuration
public class MainController extends WebMvcConfigurerAdapter {
 
	@Autowired
	ProductRepository product_repository;
	
   @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
   public String welcomePage(Model model) {
       return "index";
   }
	 
   	@RequestMapping("/allProducts")
	public String index(Model model){
		List<Product> product_list = product_repository.findAll();
		model.addAttribute("product_list", product_list);
	return "allProducts";
		   	}
	   
	   @RequestMapping("/product/{id}")
	public String product(Model model){
		//using id=1 as a test
	List<Product> product = product_repository.findByid(1);
	model.addAttribute("product_id", product);
	return "product";
	}
	   
   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }
	 
}
