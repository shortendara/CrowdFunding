package ie.shorten.test.entity;

import java.util.Date;
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
	private String productDescription;
	private double productGoal;
	private double currentRasied;
	

	@ManyToMany(mappedBy="products")
	private List<User> users;
	
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
		return currentRasied;
	}

	public void setCurrentRasied(double currentRasied) {
		this.currentRasied = currentRasied;
	}


	@Override
	public String toString() {
		return "Movement [id=" + id + ", name=" + productName + "]";
	}
}
