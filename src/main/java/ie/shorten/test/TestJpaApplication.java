package ie.shorten.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.shorten.test.entity.Product;
import ie.shorten.test.repository.ProductRepository;

@EnableAutoConfiguration
public class TestJpaApplication implements CommandLineRunner{

	@Autowired
	ProductRepository product_repository;
	
	public void query01() {
		System.out.println("\nQuery 1 (Print artist with id 1)\n------------------");
		
		// using inherited findOne method from CrudRepository
		Product artist = product_repository.findOne(1);

		System.out.println(artist.toString());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TestJpaApplication.class, args);
	}
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		query01();
	}
}
