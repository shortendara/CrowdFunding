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
	public List<Product> findByproductName(String productName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	public List<Product> findByproductNameContains(String productName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
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
	public List<Product> findByid(int id);
	
	public List<Product> findByuser_id(int id);
	
	@Transactional
	List<Product> removeByid(int id);
	
}
