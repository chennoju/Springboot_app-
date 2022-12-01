package com.project.myapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity(name="users_table")
//@JsonIgnoreProperties("password")
@JsonFilter("SomeBeanFilter")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int id;
	
	@NotNull
	private String fname;
	
	@NotNull
	private String lname;
	
	@NotNull
	private String gender;
	
	@NotNull
	@Column(unique=true)
	private String phonenumber;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String password;
	
	

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<CartDetails> cart;
	
	
	
	
	public List<CartDetails> getCart() {
		return cart;
	}
	public void setCart(List<CartDetails> cart) {
		this.cart = cart;
	}
	
		
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", phonenumber="
				+ phonenumber + ", email=" + email + ", password=" + password + ", cart=" + cart + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
