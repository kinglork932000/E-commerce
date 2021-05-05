package com.onlinestore.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlinestore.domain.security.Authority;


@Entity
@Table(name="users")
public class User implements UserDetails{

	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private String username;
	private String fullname;
	@Column(nullable=false)
	private String password;
	@Column()
	private Date dateofbirth;
	@Column()
	private String phone;
	@Column(nullable=false, unique=true)
	private String email;
	private String address;
	@Column(nullable=false, columnDefinition = "bit default 0")
	private boolean type;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CartItem> cartitems  = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Order> orders  = new HashSet<>();
	
	@Transient
	private boolean enabled = true;
	@Transient
	private BigDecimal GrandTotal;

	public BigDecimal getGrandTotal() {
		return GrandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		GrandTotal = grandTotal;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Type(type = "numberic_boolean")
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
			if(type)
				authorities.add(new Authority("ROLE_ADMIN"));
			else
				authorities.add(new Authority("ROLE_USER"));
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	
	
}
