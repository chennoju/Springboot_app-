package com.project.myapp.myappdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.myapp.model.CartDetails;


public interface CartDao extends JpaRepository<CartDetails,Integer> {

	@Query("DELETE u FROM CartDetails u WHERE u.id = ?1 and u.c_id = ?2")
	public int deleteItems(int id,int c_id);
}
