package com.example.HotelOrderMgmntDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.HotelOrderMgmntDemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}