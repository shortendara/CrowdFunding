package ie.shorten.test.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="userName")
	private String userName;
	
	private String password;
	
	/*
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="users_products",
			joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="product_id", referencedColumnName="id")})
	public List<Product> products;
	*/
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
	private List<Product> products;
	
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
