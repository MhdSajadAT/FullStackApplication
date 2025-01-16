package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.ServicesCategory;
@Repository
public interface ServicesRepostory extends JpaRepository<ServicesCategory, Integer> {

   
}