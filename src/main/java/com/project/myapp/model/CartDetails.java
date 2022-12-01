package com.project.myapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CartDetails {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(unique=true)
	
	private int cart_id;

	@Override
	public String toString() {
		return "CartDetails [id=" + id + ", cart_id=" + cart_id + "]";
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	
	

}
