package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Customers;
@Repository
public interface CustomerReposirtory extends JpaRepository<Customers, Integer> {
   
}