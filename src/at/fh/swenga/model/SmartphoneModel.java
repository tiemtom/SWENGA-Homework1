package at.fh.swenga.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Smartphone")

@NamedQueries({
		@NamedQuery(name = "SmartphoneModel.findByNameOrBrand", 
		query = "SELECT s FROM SmartphoneModel AS s JOIN s.brand AS b "
				+ "WHERE LOWER(b.name) LIKE CONCAT('%',LOWER(:searchString), '%')"
				+ "OR LOWER(s.name) LIKE CONCAT('%',LOWER(:searchString), '%')"),
		@NamedQuery(name = "SmartphoneModel.countByBrand", 
		query = "SELECT COUNT(s) FROM SmartphoneModel AS s JOIN s.brand as b "
				+ "WHERE LOWER(b.name) LIKE CONCAT('%',LOWER(:searchString), '%')") 
		})

public class SmartphoneModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private BrandModel brand;

	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	private Calendar releaseDate;

	@Min(value = 0L, message = "Value must be positive")
	@Column(nullable = false)
	private double price;

	public SmartphoneModel() {
	}

	public SmartphoneModel(String name, Calendar releaseDate, double price) {
		super();
		this.name = name;
		this.releaseDate = releaseDate;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrandModel getBrand() {
		return brand;
	}

	public void setBrand(BrandModel brand) {
		this.brand = brand;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
