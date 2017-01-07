package ie.shorten.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.shorten.test.entity.Product;
import ie.shorten.test.entity.User;
import ie.shorten.test.repository.PledgeRepository;
import ie.shorten.test.repository.ProductRepository;
import ie.shorten.test.repository.UserRepository;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	ProductRepository product_repository;
	@Autowired
	UserRepository user_repository;
	@Autowired
	PledgeRepository pledge_repository;
	
	Authentication auth;
	
	/**
	 * 
	 * @param model
	 * @return Web page listing all products
	 */
   	@RequestMapping("/all")
	public String index(Model model){
   		//Find all products within the database
		List<Product> product_list = product_repository.findAll();
		model.addAttribute("product_list", product_list);
		
		try{
			//Find current user that is logged in
			String user_name = auth.getName();
			User user = user_repository.findByuserName(user_name);
			model.addAttribute("user", user);
		}catch(NullPointerException ne){
			ne.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Return all_products page
		return "allProducts";
	}
   	
   	/**
   	 * Returns web page of specific project given ID
   	 * @param model
   	 * @return Product web page
   	 */
   	@RequestMapping("/{id}")
	public String product(Model model, @PathVariable int id){
	   	/*Get user that is logged in*/
		auth = SecurityContextHolder.getContext().getAuthentication();
		String user_name = auth.getName();
		User user = user_repository.findByuserName(user_name);
		model.addAttribute("user", user);
		/*Retrieve product based on ID*/
		Product product = product_repository.findByid(id);
		model.addAttribute("product", product);
		return "product";
	}

}
