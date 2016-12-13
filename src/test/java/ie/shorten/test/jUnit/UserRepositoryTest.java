/**
 * 
 */
package ie.shorten.test.jUnit;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ie.shorten.test.entity.User;
import ie.shorten.test.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
 
    @Mock
    private UserRepository UserRepo;
    
    @Test
    public void findByUserId() {
 
        // Expected objects
        User expectedUser = new User();
        expectedUser.setCredit(1000);
        expectedUser.setId(1);
        expectedUser.setPassword("password");
        expectedUser.setUserName("dara_shorten");
    	
        // Mockito expectations                            
        when(UserRepo.findByuserName("dara_shorten")).thenReturn(expectedUser);
 
        // Execute the method being tested     
        User newUser = UserRepo.findByuserName("dara_shorten");
 
        // Validation  
        assertNotNull(newUser);
        assertEquals(expectedUser,newUser);

    }
}