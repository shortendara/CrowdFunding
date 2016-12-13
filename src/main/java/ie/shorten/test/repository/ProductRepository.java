package ie.shorten.test.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ie.shorten.test.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

	/**
	 * Find products by product name
	 * @Param productName
	 * @return Product
	 * */
	public Product findByproductName(String productName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	
	/**
	 * Used for search functionality. 
	 * @param productName
	 * @return
	 */
	public List<Product> findByproductNameContainsIgnoreCase(String productName); // SELECT * FROM artists WHERE LOWER(fullName) LIKE LOWER('%xxxxx%')
	
	/**
	 * Find all products that available
	 * @return All Products
	 */
	public List<Product> findAll();
	
	/**
	 * Find product by sepcific id number
	 * @param id
	 * @return Product
	 */
	public Product findByid(int id);
	
	/**
	 * Function to find all products owned by a user based on user id
	 * @param id
	 * @return List of products with given user id number
	 */
	public List<Product> findByuser_id(int id);
	
	/**
	 * Function to remove a product. Called when goal is reached or date has run out
	 * @param id
	 * @return List of remaining products
	 */
	@Transactional
	List<Product> removeByid(int id);
	
}
