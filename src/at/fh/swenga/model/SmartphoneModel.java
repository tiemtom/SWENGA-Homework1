package at.fh.swenga.model;

import java.util.Date;

public class SmartphoneModel {
	
	private int id;
	private String name;
	private String brand;
	private Date releaseDate;
	private Integer price;
	
	// getters and setters
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	// constructors
	public SmartphoneModel() {
		
	}
	
	public SmartphoneModel(int id, String name, String brand, Date releaseDate, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.releaseDate = releaseDate;
		this.price = price;
	}

	// hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartphoneModel other = (SmartphoneModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	// toString
	@Override
	public String toString() {
		return "SmartphoneModel [id=" + id + ", name=" + name + ", brand=" + brand + ", releaseDate=" + releaseDate
				+ ", price=" + price + "]";
	}
	
	
	
	
}

	