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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name="product_name")
	@Size(min=2, max=255)
	private String productName;
	
	@NotNull
	@Column(name="product_description")
	@Size(min=2)
	private String productDescription;
	
	@NotNull
	@Column(name="product_goal")
	@Min(1)
	private double productGoal;
	
	@Column(name="current_raised")
	private double currentRaised;
	
	/**
	 * A user may have many products
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	/**
	 * A product may have many pledges may to it
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="product")
	private List<Pledge> pledges;
	
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date start_date;
	
	@NotNull
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date end_date;
	
	public Product(){}
	
	public Product(int id, String productName, String productDescription, double productGoal, double currentRaised, Date start_date, Date end_date){
		this.id=id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productGoal = productGoal;
		this.currentRaised = currentRaised;
		this.start_date = start_date;
		this.end_date = end_date;
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
		return calculate_current_raised(pledges);
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
	
	public List<Pledge> getPledges() {
		return pledges;
	}

	public void setPledges(List<Pledge> pledges) {
		this.pledges = pledges;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + productName + "]";
	}
}
