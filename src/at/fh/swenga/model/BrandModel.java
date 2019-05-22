package at.fh.swenga.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class BrandModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private Set<SmartphoneModel> smartphones;

	@Version
	long version;

	public BrandModel() {
	}

	public BrandModel(String name) {
		super();
		this.name = name;
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

	public Set<SmartphoneModel> getSmartphones() {
		return smartphones;
	}

	public void setSmartphones(Set<SmartphoneModel> smartphones) {
		this.smartphones = smartphones;
	}


	public void addSmartphone(SmartphoneModel smartphone) {
		if (smartphones == null) {
			smartphones = new HashSet<SmartphoneModel>();
		}
		smartphones.add(smartphone);
	}

}
