package com.example.HotelOrderMgmntDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelOrderMgmntDemo.entity.UserOrder;



@Repository
public interface HotelOrderRepository extends JpaRepository<UserOrder, Long> {

}