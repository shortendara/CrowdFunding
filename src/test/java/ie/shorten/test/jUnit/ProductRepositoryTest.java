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

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;
	
@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {
 
    @Mock
    private ProductRepository productRepo;
 
    @Test
    public void findAll() {
 
        // Expected objects
        List<Product> expectedProductList = productRepo.findAll();
 
        // Mockito expectations                            
        when(productRepo.findAll()).thenReturn(expectedProductList);
 
        // Execute the method being tested     
        List<Product> newProductList = productRepo.findAll();
 
        // Validation  
        assertNotNull(newProductList);
        assertEquals(expectedProductList,newProductList);

    }
}