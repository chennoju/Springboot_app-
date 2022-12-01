package com.project.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.project.myapp.model.CartDetails;
import com.project.myapp.model.CartItem;
import com.project.myapp.model.User;
import com.project.myapp.myappdao.CartDao;
import com.project.myapp.myappdao.MyappDao;

@RestController
@CrossOrigin(origins = "*")
public class Cart {
	
	@Autowired
	WebClient.Builder webclient;
	
	@Autowired
	MyappDao repo;
	
	@Autowired
	CartDao repo1;
	
	@GetMapping("cart/{id}")
	public ResponseEntity<CartItem> getCart(@PathVariable("id") int id) {
		
		String url="https://dummyjson.com/products/"+id;
		
		CartItem obj=webclient.build()
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(CartItem.class)
				.block();
		System.out.print(obj);
		return new ResponseEntity<>(obj	,HttpStatus.OK);
		
	}
	
	
	@GetMapping("users/{id}/cart")
	public List<CartItem> getuserCart(@PathVariable("id") int id) {
		Optional<User> opt=repo.findById(id);
		if(opt==null)
			return null;
		User user=opt.get();
		
		List<CartDetails> carts=user.getCart();
		
		List<CartItem> items=new ArrayList<>();
		
		for(CartDetails cart:carts) {
			String url="https://dummyjson.com/products/"+cart.getCart_id();
			
			
			CartItem obj=webclient.build()
					.get()
					.uri(url)
					.retrieve()
					.bodyToMono(CartItem.class)
					.block();
			items.add(obj);
		}
		
		
		
		return items;
		
	}

	
	
	
	@GetMapping("users/{id}/cart/{c_id}")
	public CartItem getuserCartById(@PathVariable("id") int id,@PathVariable("c_id") int c_id) {
		Optional<User> opt=repo.findById(id);
		if(opt==null)
			return null;
		User user=opt.get();
		
		List<CartDetails> carts=user.getCart();
		
		
		
		CartDetails  item=carts.get(c_id-1);
		
		
			String url="https://dummyjson.com/products/"+item.getCart_id();;
			
			
			CartItem obj=webclient.build()
					.get()
					.uri(url)
					.retrieve()
					.bodyToMono(CartItem.class)
					.block();
			
		
		
		
		return obj;
		
	}

	
	
//	
//	@PostMapping("cart/{id}")
//	public ResponseEntity<CartItem> addCart(@PathVariable("id") int id) {
//		
//		String url="https://dummyjson.com/products/"+id;
//		
//		CartItem obj=webclient.build()
//				.get()
//				.uri(url)
//				.retrieve()
//				.bodyToMono(CartItem.class)
//				.block();
//		System.out.print(obj);
//		return new ResponseEntity<>(obj	,HttpStatus.OK);
//		
//	}
//	
//	public  CartItem getCartForUser(@PathVariable("id") int id) {
//		
//		String url="https://dummyjson.com/products/"+id;
//		
//		CartItem obj=webclient.build()
//				.get()
//				.uri(url)
//				.retrieve()
//				.bodyToMono(CartItem.class)
//				.block();
//		return obj;
//		
//	}
//	
//	
	
	@PostMapping("users/{id}/cart")
	public MappingJacksonValue postuserCart(@PathVariable("id") int id,@RequestBody CartDetails cart) {
		Optional<User> opt=repo.findById(id);
		if(opt==null)
			return null;
		User user=opt.get();
		

		user.getCart().add(cart);
		
		System.out.println(user.toString());
		repo.save(user);
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("fname","cart");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(user);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
		
	}
	
	@DeleteMapping("users/{id}/cart/{c_id}")
	public String deleteCart(@PathVariable("id") int id,@PathVariable("c_id") int c_id) {
//		Optional<User> opt=repo.findById(id);
//		User user=opt.get();
//		
//			List<CartDetails> list=user.getCart();
//			for(CartDetails l:list) {
//				if(l.getCart_id()==c_id)
//				{
//					System.out.print(l.getCart_id());
//					repo1.delete(l);
//					break;
//				}
//			}
//			
//			user.setCart(list);
//			System.out.print(user.toString());
//			
//
//			SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("fname","cart");
//			FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
//			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(user);
//			mappingJacksonValue.setFilters(filters);
//			return mappingJacksonValue;
		
		int c=repo1.deleteItems(id, c_id);
		return "sucess";
		
		
	}


	
	
}
