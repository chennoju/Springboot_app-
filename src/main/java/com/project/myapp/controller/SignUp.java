package com.project.myapp.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.project.myapp.controller.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.project.myapp.model.CartDetails;
import com.project.myapp.model.User;
import com.project.myapp.myappdao.MyappDao;


@RestController
@CrossOrigin(origins="*")
public class SignUp {
	
	@Autowired
	MyappDao repo;
	static int id=1;
	
	@PostMapping("/users")
	public ResponseEntity addUser(@RequestBody User user) {
		
		user.setId(id);
		id++;
		System.out.println(user.toString());
		try {
		User one=repo.save(user);
		
		
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();}
		
		catch(Exception e) {
			
			id--;
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/users")
	public ResponseEntity getUsers() {
		List<User> users=repo.findAll();
		List<EntityModel<User>> allentitymodel=new ArrayList<>();
		for(User user:users) {
		
			allentitymodel.add(entityModel(user));
		}
		
		CollectionModel<EntityModel<User>> model=CollectionModel.of(allentitymodel);
		model.add(linkTo(methodOn(this.getClass()).getUsers()).withSelfRel());

		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("fname","lname","gender","email","phonenumber","cart");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(model);
		mappingJacksonValue.setFilters(filters);
		return new ResponseEntity<>(mappingJacksonValue,HttpStatus.OK);
	}
	@GetMapping("/users/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") int id) {
		Optional<User> optional=repo.findById(id);
		User user=optional.get();
		
		System.out.println(user.getCart());
		EntityModel<User> entitymodel=entityModel(user);

		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("fname","lname","gender","email","phonenumber");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(entitymodel);
		
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	
	private EntityModel<User> entityModel(User user) {
		EntityModel<User> entitymodel=EntityModel.of(user);
		entitymodel.add(linkTo(methodOn(this.getClass()).getUsers()).withRel("all-collection"));
		entitymodel.add(linkTo(methodOn(this.getClass()).getUserById(user.getId())).withSelfRel());
		entitymodel.add(linkTo(methodOn(this.getClass()).deleteUser(user.getId())).withRel("delete"));
		entitymodel.add(linkTo(methodOn(this.getClass()).updateUser(user)).withRel("update"));
		entitymodel.add(linkTo(methodOn(Cart.class).getuserCart(user.getId())).withRel("all cart items"));
		List<CartDetails> carts=user.getCart();
		for(CartDetails c:carts) {
			System.out.println(c.getCart_id());
			entitymodel.add(linkTo(methodOn(Cart.class).getuserCartById(user.getId(),c.getCart_id())).withRel("cart item by id"));
		}
		return entitymodel;
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<CollectionModel<EntityModel<User>>> deleteUser(@PathVariable int id) {
		User emp=repo.getOne(id);
		repo.delete(emp);
		return getUsers();
		
	}
	@PutMapping("/users")
	public ResponseEntity<CollectionModel<EntityModel<User>>> updateUser(@RequestBody User user) {
		repo.save(user);
		return getUsers();
		}
	
	
}
