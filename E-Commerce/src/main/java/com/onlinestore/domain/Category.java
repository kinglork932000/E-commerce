package com.onlinestore.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	private String categoryID;
	private String name;
	private String biggroup;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Product> products  = new HashSet<>();
	
	
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBigGroup(String biggroup) {
		this.biggroup = biggroup;
	}
	public String getBigGroup() {
		return biggroup;
	}
	public Set<Product> getProducts(){
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
