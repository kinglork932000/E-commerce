package com.onlinestore.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cartitems")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartitem_id;
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(nullable = false, name = "username")
	private User user;
	@Column(nullable=false)
	private int quantity;
	
	@Transient
	private BigDecimal subtotal;

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public void setCartitem_id (int cartitem_id) {
		this.cartitem_id = cartitem_id;
	}
	public int getCartitem_id () {
		return cartitem_id;
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
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
