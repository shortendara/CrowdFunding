package ie.shorten.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;

@SpringBootApplication
public class TestJpaApplication{

	/*
	@Autowired
	ProductRepository product_repository;
	
	public void query01() {
		System.out.println("\nQuery 1 (Print artist with id 1)\n------------------");
		
		// using inherited findOne method from CrudRepository
		List<Product> products = product_repository.findAll();

		for(Product product : products){
			System.out.println(product.toString());
		}
		
	}
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(TestJpaApplication.class, args);
	}
}
