package com.project.myapp.myappdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.myapp.model.User;



public interface MyappDao extends JpaRepository<User,Integer> {
	
	
	public User findByEmail(String email);
	
	
}
