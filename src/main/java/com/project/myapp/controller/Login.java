package com.project.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.project.myapp.model.User;
import com.project.myapp.myappdao.MyappDao;

@RestController
@CrossOrigin(origins = "*")
public class Login {
	
	@Autowired
	MyappDao repo;
	@GetMapping("/users/email/{email}")
	public MappingJacksonValue getUserName(@PathVariable("email") String email) {
		User user=repo.findByEmail(email);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("email","password","fname","id");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(user);
		
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
