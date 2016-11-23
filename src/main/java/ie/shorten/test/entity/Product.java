package ie.shorten.test.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="prodcut_description")
	private String productDescription;
	
	@Column(name="product_goal")
	private double productGoal;
	
	@Column(name="current_raised")
	private double currentRaised;
	
	@ManyToMany(mappedBy="products")
	private List<User> users;
	
	public Product(){}
	
	public Product(int id, String productName, String productDescription, double productGoal, double currentRaised){
		this.id=id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productGoal = productGoal;
		this.currentRaised = currentRaised;
	}
	
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


	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductGoal() {
		return productGoal;
	}

	public void setProductGoal(double productGoal) {
		this.productGoal = productGoal;
	}

	public double getCurrentRasied() {
		return currentRaised;
	}

	public void setCurrentRasied(double currentRaised) {
		this.currentRaised = currentRaised;
	}


	@Override
	public String toString() {
		return "Movement [id=" + id + ", name=" + productName + "]";
	}
}
