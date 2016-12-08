package ie.shorten.test.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name="product_name")
	private String productName;
	
	@NotNull
	@Column(name="product_description")
	private String productDescription;
	
	@NotNull
	@Column(name="product_goal")
	private double productGoal;
	
	@Column(name="current_raised")
	private double currentRaised;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="product")
	private List<Pledge> pledges;
	
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date start_date;
	
	@NotNull
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date end_date;
	
	private String youtube_url;
	
	public Product(){}
	
	public Product(int id, String productName, String productDescription, double productGoal, double currentRaised, Date start_date, Date end_date, String youtube_url){
		this.id=id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productGoal = productGoal;
		this.currentRaised = currentRaised;
		this.start_date = start_date;
		this.end_date = end_date;
		this.youtube_url = youtube_url;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
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

	public double getCurrentRaised() {
		return currentRaised;
	}

	public void setCurrentRaised(double currentRaised) {
		this.currentRaised = currentRaised;
	}
	
	private Double calculate_current_raised(List<Pledge> pledges){
		double total_raised = 0;
		for(Pledge pledge : pledges){
			total_raised += pledge.getAmount();
		}
		setCurrentRaised(total_raised);
		return total_raised;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getYoutube_url() {
		return youtube_url;
	}

	public void setYoutube_url(String youtube_url) {
		this.youtube_url = youtube_url;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + productName + "]";
	}
}
