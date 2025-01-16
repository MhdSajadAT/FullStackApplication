package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
   
}