package ie.shorten.test.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;
import ie.shorten.test.repository.UserRepository;
 
@Configuration
@Controller
public class MainController extends WebMvcConfigurerAdapter {
 
	@Autowired
	ProductRepository product_repository;
	@Autowired
	UserRepository user_repository;
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
		List<Product> product_list = product_repository.findAll();
		model.addAttribute("product_list", product_list);
		return "allProducts";
	}
   
   	/**
   	 * Web page has authority to delete products
   	 * @param model
   	 * @return Admin web page which has privligages to delete projects
   	 */
   	@RequestMapping(value = "/admin", method = RequestMethod.GET)
   	public String adminPage(Model model) {
   		return "adminPage";
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
   	@RequestMapping(value = "/user/{id}/profile", method = RequestMethod.GET)
   	public String user_profile(Model model, @PathVariable int id) {
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		System.out.println(user_name);
   		//model.addAttribute();
   		return "user_profile";
   	}
   	
   	/**
   	 * 
   	 * @param model
   	 * @return Web page containing user's products
   	 */
   	@RequestMapping(value = "/user/{id}/products", method = RequestMethod.GET)
   	public String user_products(Model model) {
 
   		return "user_products";
   	}
   
   	/**
   	 * Returns web page of specific project given ID
   	 * @param model
   	 * @return Product web page
   	 */
   	@RequestMapping("/product/{id}")
	public String product(Model model, @PathVariable int id){
		//using id=1 as a test
	List<Product> product = product_repository.findByid(id);
	model.addAttribute("product", product);
	return "product";
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
