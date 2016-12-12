package ie.shorten.test.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.shorten.test.entity.Pledge;
import ie.shorten.test.entity.Product;
import ie.shorten.test.entity.User;
import ie.shorten.test.repository.PledgeRepository;
import ie.shorten.test.repository.ProductRepository;
import ie.shorten.test.repository.UserRepository;
 
@Configuration
@Controller
public class MainController extends WebMvcConfigurerAdapter {
 
	@Autowired
	ProductRepository product_repository;
	@Autowired
	UserRepository user_repository;
	@Autowired
	PledgeRepository pledge_repository;
	
	Authentication auth;
	 @Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }
	 
	/**
	 * 
	 * @param model
	 * @return Web page listing all products
	 */
   	@RequestMapping("/product/all")
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
   	@RequestMapping("/product/{id}")
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
   
   	/**
   	 * Web page has authority to delete products
   	 * @param model
   	 * @return Admin web page which has privligages to delete projects
   	 */
   	@RequestMapping(value = "/admin", method = RequestMethod.GET)
   	public String adminPage(Model model) {
   		List<Product> all_products = product_repository.findAll();
   		model.addAttribute("all_products", all_products);
   		return "adminPage";
   	}
   	
   	@PostMapping(value="admin/remove/product/{product_id}")
   	public String remove_product(@PathVariable int product_id){
   		product_repository.removeByid(product_id);
   		return "redirect:/admin";
   	}
   	
   	/**
   	 * Returns web page indicating that user has logged out successfully 
   	 * @param model
   	 * @return logout successful Web page 
   	 */
   	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   	public String logoutSuccessfulPage(Model model) {
   		model.addAttribute("title", "Logout");
   		return "logoutSuccessfulPage";
   	}
 
   	/**
   	 * Returns a user's profile page
   	 * @param model
   	 * @return User Profile web page
   	 */
   	@RequestMapping(value = "/user/profile/{id}", method = RequestMethod.GET)
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
   	
   	@RequestMapping(value="/user/{user_id}/product/create", method=RequestMethod.GET)
   	public String get_create_product(){
   		return "new_product";
   	}
   	
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
   	
   	@PostMapping(value = "/user/update/product/{product_id}")
   	public String product_update_submit(@ModelAttribute Product product, @PathVariable("product_id") int product_id){
   		Product current_product = product_repository.findByid(product_id);
   		current_product.setProductDescription(product.getProductDescription());
   		product_repository.save(current_product);
   		System.out.println(product.getProductDescription());
   		return "redirect:/product/all";
   	}
   
   	
   	@PostMapping(value="/donate/{product_id}")
   	public String donate(@ModelAttribute Pledge pledge, @PathVariable int product_id){
   		auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		User user = user_repository.findByuserName(user_name);
   		List<Product> user_products = user.getProducts();
   		/*
   		for(Product product : user_products){
   			if(product.getId() == product_id){
   				
   			}
   		}
   		*/
   		if(pledge.getAmount() > user.getCredit()){
   			
   		}
   		pledge.setUser(user);
   		pledge.setProduct(product_repository.findByid(product_id));
   		pledge_repository.save(pledge);
   		return "redirect:/product/all";
   	}
   	
   	@PostMapping(value="donate/{product_id}/cancel")
   	public String cancel_donation(@PathVariable int product_id){
   		auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		User user = user_repository.findByuserName(user_name);
   		
   		/*Find pledges for given user*/
   		List<Pledge> pledges = pledge_repository.findByuser_id(user.getId());
   		
   		/*Remove pledge for given product id*/
   		for(Pledge pledge: pledges){
   			if(pledge.getProduct().getId() == product_id){
   				pledge_repository.delete(pledge);
   			}
   		}
   		
   		return "redirect:/user/profile/"+user.getId();
   	}
   	/**
   	 * Return error page if user hasn't permission to view page
   	 * @param model
   	 * @param principal
   	 * @return Error Web page
   	 */
   	@RequestMapping(value = "/404", method = RequestMethod.GET)
   	public String accessDenied(Model model, Principal principal) {    
       if (principal != null) {
           model.addAttribute("message", "Hi " + principal.getName()
                   + "<br> You do not have permission to access this page!");
       } else {
           model.addAttribute("msg",
                   "You do not have permission to access this page!");
       }
       return "403Page";
   	}
}
