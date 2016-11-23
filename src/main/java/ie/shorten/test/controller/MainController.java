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
@Controller
public class MainController extends WebMvcConfigurerAdapter {
 
	@Autowired
	ProductRepository product_repository;
	 @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }
	/**
	 * 
	 * @param model
	 * @return Web page listing all products
	 */
   	@RequestMapping("/allProducts")
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
   	 * @param principal
   	 * @return User Profile web page
   	 */
   	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
   	public String userInfo(Model model, Principal principal) {
   		// After user login successfully.
   		String userName = principal.getName();
 
   		System.out.println("User Name: "+ userName);
 
   		return "userInfoPage";
   	}
   
   	/**
   	 * Returns web page of specific project given ID
   	 * @param model
   	 * @return Product web page
   	 */
   	@RequestMapping("/product/{id}")
	public String product(Model model){
		//using id=1 as a test
	List<Product> product = product_repository.findByid(1);
	model.addAttribute("product_id", product);
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
