package ie.shorten.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.shorten.test.entity.Pledge;
import ie.shorten.test.entity.Product;
import ie.shorten.test.entity.User;
import ie.shorten.test.repository.UserRepository;
import ie.shorten.test.repository.ProductRepository;
import ie.shorten.test.repository.PledgeRepository;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	ProductRepository product_repository;
	@Autowired
	UserRepository user_repository;
	@Autowired
	PledgeRepository pledge_repository;
	
	Authentication auth;
	/**
   	 * Returns a user's profile page
   	 * @param model
   	 * @return User Profile web page
   	 */
   	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
   	public String user_profile(Model model, @PathVariable int id) {
   		auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		User user = user_repository.findByuserName(user_name);
   		model.addAttribute("user", user);
   		
   		/*Find products by user id*/
   		List<Product> user_products = product_repository.findByuser_id(id);
   		model.addAttribute("user_products", user_products);
   		
   		/*Find all pledges by user*/
   		List<Pledge> user_pledges = pledge_repository.findByuser_id(id);
   		model.addAttribute("user_pledges", user_pledges);
   		return "user_profile";
   	}
   	
   	/**
   	 * Returns user the web page in which products can be created
   	 * @return Create product page
   	 */
   	@RequestMapping(value="/{user_id}/product/create", method=RequestMethod.GET)
   	public String get_create_product(){
   		return "new_product";
   	}
   	
   	/**
   	 * Post Mapping to save product created by user
   	 * @param product
   	 * @return Return product page of newly created product
   	 */
   	@PostMapping(value="/user/product/create")
   	public String post_create_product(@ModelAttribute Product product){
   		System.out.println(product.getProductName());
   		product_repository.save(product);
   		return "redirect:/product/"+product.getId();
   	}
   	
   	/**
   	 * 
   	 * @param model
   	 * @return Web page containing user's products
   	 */
   	@RequestMapping(value = "/user/{user_id}/product/edit/{id}", method = RequestMethod.GET)
   	public String product_update(Model model, @PathVariable int id) {
   		auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		User user = user_repository.findByuserName(user_name);
   		model.addAttribute("user", user);
   		
   		/*Find product id*/
   		model.addAttribute("product", product_repository.findByid(id));
   		return "user_product_edit";
   	}
   	
   	/**
   	 * Post function in which user updates product. Only description of product may be updated from
   	 * the web page itself. No other varaibles are allowed to be changed.
   	 * @param product
   	 * @param product_id
   	 * @return All products page
   	 */
   	@PostMapping(value = "/user/update/product/{product_id}")
   	public String product_update_submit(@ModelAttribute Product product, @PathVariable("product_id") int product_id){
   		Product current_product = product_repository.findByid(product_id);
   		current_product.setProductDescription(product.getProductDescription());
   		product_repository.save(current_product);
   		System.out.println(product.getProductDescription());
   		return "redirect:/product/all";
   	}

}
