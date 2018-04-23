package com.zhenhua.web.po;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="mongo_collection_home")
public class Home implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Field
	private String address;
	
	@Field
	private String homeName;

	@Transient
	private String city;
	
	@Field
	private List<Person> persons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Home [id=" + id + ", address=" + address + ", homeName=" + homeName + ", city=" + city + ", persons="
				+ persons + "]";
	}
}
