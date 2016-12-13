/**
 * 
 */
package ie.shorten.test.jUnit;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.shorten.test.entity.Pledge;
import ie.shorten.test.repository.PledgeRepository;

@RunWith(MockitoJUnitRunner.class)
public class PledgeRepositoryTest {
 
    @Mock
    private PledgeRepository pledgeRepo;
    
    @Test
    public void findByPledgeId() {
 
        // Expected objects
        List<Pledge> expectedPledge = pledgeRepo.findByuser_id(1);
 
        // Mockito expectations                            
        when(pledgeRepo.findAll()).thenReturn(expectedPledge);
 
        // Execute the method being tested     
        List<Pledge> newPledge = pledgeRepo.findByuser_id(1);
 
        // Validation  
        assertNotNull(newPledge);
        assertEquals(expectedPledge,newPledge);

    }
}
