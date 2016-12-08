package ie.shorten.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ie.shorten.test.entity.Pledge;

public interface PledgeRepository extends CrudRepository<Pledge, Integer>{

	public List<Pledge> findAll();
	
	public List<Pledge> findByid(int id);
	
	/**
	 * 
	 * @param user_id
	 * @return Pledges for user of user_id
	 */
	public List<Pledge> findByuser_id(int user_id);
	
	/**
	 * 
	 * @param product_id
	 * @return Pledges for product
	 */
	public List<Pledge> findByproduct_id(int product_id);
}
