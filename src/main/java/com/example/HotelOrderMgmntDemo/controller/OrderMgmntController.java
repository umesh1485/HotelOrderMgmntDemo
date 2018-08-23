package com.example.HotelOrderMgmntDemo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelOrderMgmntDemo.entity.UserOrder;
import com.example.HotelOrderMgmntDemo.event.OrderEvent;
import com.example.HotelOrderMgmntDemo.repository.HotelOrderRepository;
import com.example.HotelOrderMgmntDemo.repository.UserRepository;

@RequestMapping("/orderMgmnt")
@RestController
public class OrderMgmntController implements ApplicationEventPublisherAware{

	@Autowired
	HotelOrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	 private ApplicationEventPublisher publisher;

	@GetMapping("/hotelOrders")
	public List<UserOrder> getAllHotelOrders() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return orderRepository.findAll(sortByCreatedAtDesc);
	}

	@PostMapping("/hotelOrders")
	public UserOrder createOrder(@Valid @RequestBody UserOrder order) {

		Date date = new Date();
		order.setCreatedAt(date);
		order.setUpdatedDate(date);
		return orderRepository.save(order);
	}

	@GetMapping(value = "/hotelOrders/{id}")
	public ResponseEntity<UserOrder> getHotelOrderById(@PathVariable("id") Long id) {

		Optional<UserOrder> order = orderRepository.findById(id);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/hotelOrders/{id}")
	public ResponseEntity<UserOrder> updateOrder(@PathVariable("id") Long id,
			@Valid @RequestBody UserOrder updateOrder) {
		Optional<UserOrder> existingDetails = orderRepository.findById(id);
		if (!existingDetails.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UserOrder order = existingDetails.get();
		order.setStatus(updateOrder.getStatus());
		order.setDescription(updateOrder.getDescription());
		order.setAddress(updateOrder.getAddress());
		order.setPhone(updateOrder.getPhone());
		order.setUpdatedBy(updateOrder.getUpdatedBy());
		order.setUpdatedDate(new Date());
		
		UserOrder updatedOrder = orderRepository.save(order);

		//publishing the event here
		System.out.println(order+"Updated.........");
        publisher.publishEvent(new OrderEvent(this, "Update", order));
        
		return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		 this.publisher = publisher;
	}

}
