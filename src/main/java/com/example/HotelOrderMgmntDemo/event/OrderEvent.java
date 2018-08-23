package com.example.HotelOrderMgmntDemo.event;

import org.springframework.context.ApplicationEvent;

import com.example.HotelOrderMgmntDemo.entity.UserOrder;

public class OrderEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;

	private String eventType;
	private UserOrder order;

	// Constructor's first parameter must be source
	public OrderEvent(Object source, String eventType, UserOrder order) {
		// Calling this super class constructor is necessary
		super(source);
		this.eventType = eventType;
		this.order = order;
	}

	public String getEventType() {
		return eventType;
	}

	public UserOrder getOrder() {
		return order;
	}
}