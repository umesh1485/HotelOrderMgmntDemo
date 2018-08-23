package com.example.HotelOrderMgmntDemo.controller;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HotelOrderMgmntDemo.entity.User;
import com.example.HotelOrderMgmntDemo.repository.UserRepository;

@Controller
@RequestMapping("/orderMgmnt")
public class OrderMgmntHomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "user";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "hotelAdmin";
	}
	@RequestMapping("/delivery")
	public String delivery() {
		return "deliveryGuy";
	}
	
	@PostConstruct
	public void createUsers(){
		
		User customer = new User("customer", "Umesh");
		User hotelAdmin = new User("admin", "hotel-admin");
		User deliveryGuy = new User("diliveryGuy", "hotel-delivery");
		
		userRepository.save(customer);
		userRepository.save(hotelAdmin);
		userRepository.save(deliveryGuy);
		System.out.println("--------------user created-- :"+customer);
		System.out.println("--------------user created-- :"+hotelAdmin);
		System.out.println("--------------user created-- :"+deliveryGuy);
	}
}
