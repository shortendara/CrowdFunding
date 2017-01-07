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
   	 * 
   	 * @param pledge
   	 * @param product_id
   	 * @return
   	 */
   	@PostMapping(value="/donate/{product_id}")
   	public String donate(@ModelAttribute Pledge pledge, @PathVariable int product_id){
   		auth = SecurityContextHolder.getContext().getAuthentication();
   		String user_name = auth.getName();
   		User user = user_repository.findByuserName(user_name);
   		List<Product> user_products = user.getProducts();
   		
   		/*
   		 * Redirect user to profile page if they don't have enough credit
   		 * TODO: Add error message to return page to indicate error occured.
   		 * */
   		if(pledge.getAmount() > user.getCredit()){
   			return "redirect:/product/all";
   		}
   		pledge.setUser(user);
   		pledge.setProduct(product_repository.findByid(product_id));
   		pledge_repository.save(pledge);
   		return "redirect:/product/all";
   	}
   	
   	/**
   	 * Post Mapping to cancel a donation a user made to a product
   	 * @param product_id
   	 * @return
   	 */
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
