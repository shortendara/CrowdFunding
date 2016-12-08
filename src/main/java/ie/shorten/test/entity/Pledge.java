package ie.shorten.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pledges")
public class Pledge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double amount;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Product product;
	
	public Pledge(){}

	public Pledge(int id, Double amount, User user, Product product) {
		super();
		this.id = id;
		this.amount = amount;
		this.user = user;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
