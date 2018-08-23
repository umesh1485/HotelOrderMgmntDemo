package com.example.HotelOrderMgmntDemo.event;

import org.springframework.context.ApplicationListener;

public class UserEventsProcessor implements ApplicationListener<OrderEvent>
{
    public void onApplicationEvent(OrderEvent event)
    {
    	OrderEvent orderEvent = (OrderEvent) event;
 
        System.out.println("-------Order " + orderEvent.getEventType()
                          + " with details : " + orderEvent.getOrder());
 
        // Do more processing as per application logic
    }
}