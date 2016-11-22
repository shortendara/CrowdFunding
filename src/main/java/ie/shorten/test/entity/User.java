package ie.shorten.test.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name")
	private String user_name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="users_products",
			joinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="movement_id", referencedColumnName="id")})
	public List<Product> products;
	
	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="artist_movements",
			joinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="movement_id", referencedColumnName="id")})
	public List<Product> pledges;
	*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		String out = "Artiste [id=" + id + ", fullName="
				+ user_name + ", movements="; 
		for (Product m : products) {
			out += m.toString() + ",";
		}
		out += "]]";
		return out;
	}

}
