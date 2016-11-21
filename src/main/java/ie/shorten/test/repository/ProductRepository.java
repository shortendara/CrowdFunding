package ie.shorten.test.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.shorten.test.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

	public List<Product> findByproductName(String productName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	public List<Product> findByproductNameContains(String productName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	public List<Product> findByproductNameContainsIgnoreCase(String productName); // SELECT * FROM artists WHERE LOWER(fullName) LIKE LOWER('%xxxxx%')

	public List<Product> findByid(int id);
	
}
