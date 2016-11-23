package ie.shorten.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.shorten.test.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	/**
	 * Function to retrieve user based on their user name
	 * @return User and their details
	 */
	public List<User> findByuserName(String userName);

}
