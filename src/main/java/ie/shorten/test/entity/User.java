package ie.shorten.test.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="userName")
	private String userName;
	
	private String password;
	
	private double credit;
	
	/**
	 * User may be mapped to many products
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
	private List<Product> products;
	
	/**
	 * User may donate to many products 
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
	private List<Pledge> pledges;
	
	public User(int id, String userName, String password, List<Product> products) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.products = products;
	}
	
	public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
	
	public User(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public double getCredit() {
		return calculate_credit(pledges);
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	/**
	 * Calculate total credit a user. Function called everytime profile
	 * is loaded, updated when donations are made.
	 * @param pledges
	 * @return Credit user has left
	 */
	private double calculate_credit(List<Pledge> pledges){
		double total_pledged = 0;
		for(Pledge pledge : pledges){
			total_pledged += pledge.getAmount();
		}
		setCredit(credit-total_pledged);
		return credit;
	}

	public List<Pledge> getPledges() {
		return pledges;
	}

	public void setPledges(List<Pledge> pledges) {
		this.pledges = pledges;
	}

	@Override
	public String toString() {
		String out = "User [id=" + id + ", fullName="
				+ userName + ", products="; 
		for (Product m : products) {
			out += m.toString() + ",";
		}
		out += "]]";
		return out;
	}

}
