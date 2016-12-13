/**
 * 
 */
package ie.shorten.test.jUnit;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Repository;

import ie.shorten.test.entity.Pledge;
import ie.shorten.test.entity.Product;
import ie.shorten.test.entity.User;
import ie.shorten.test.repository.PledgeRepository;
import ie.shorten.test.repository.ProductRepository;
import ie.shorten.test.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class PledgeRepositoryTest {

	UserRepository userRepo;
	
    @Mock
    private PledgeRepository pledgeRepo;
    
    @Test
    public void findByPledgeId() {
 
        // Expected objects
    	User u = (User) userRepo.findByid(1);
    	Date start = new Date();
    	Date end = new Date();
    	Product product = new Product(1, "productName", "productDescription", 1000, 100, start, end, "youtube_url");
    	Pledge p = new Pledge(1,1000.0, u, product);
        List<Pledge> expectedPledge = new ArrayList<Pledge>(); 
        expectedPledge.add(p);
        
 
        // Mockito expectations                            
        when(pledgeRepo.findAll()).thenReturn(expectedPledge);
 
        // Execute the method being tested     
        List<Pledge> newPledge = pledgeRepo.findByuser_id(1);
 
        // Validation  
        assertNotNull(newPledge);
        assertEquals(expectedPledge,newPledge);

    }
}
