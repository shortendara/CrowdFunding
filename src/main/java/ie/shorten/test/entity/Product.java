package ie.shorten.test.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String productName;

	@ManyToMany(mappedBy="products")
	private List<User> artists;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductName(String name) {
		this.productName = name;
	}

	@Override
	public String toString() {
		return "Movement [id=" + id + ", name=" + productName + "]";
	}
}
