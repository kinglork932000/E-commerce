package com.onlinestore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderitems")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderitem_id;
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(nullable = false, name = "order_id")
	private Order order;
	@Column(nullable=false)
	private int quantity;
	@Column(nullable=false)
	private double price;
	
	public void setOrderitem_id (int orderitem_id) {
		this.orderitem_id = orderitem_id;
	}
	public int getOrderitem_id () {
		return orderitem_id;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity () {
		return quantity;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Order getOrder() {
		return order;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
}
