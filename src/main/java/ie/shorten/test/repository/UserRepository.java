package ie.shorten.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.shorten.test.entity.Product;
import ie.shorten.test.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	/**
	 * Function to retrieve user based on their user name
	 * @return User and their details
	 */
	public User findByuserName(String userName);
	
	/**
	 * Find user based on user id number
	 * @param id
	 * @return User
	 */
	public List<User> findByid(int id);
	
	/**
	 * Find all products that available
	 * @return All Products
	 */
	public List<User> findAll();
	
}
