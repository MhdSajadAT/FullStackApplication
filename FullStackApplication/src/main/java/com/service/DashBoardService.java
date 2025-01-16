package com.service;
import java.util.List;

import com.entity.Customers;
import com.entity.ServicesCategory;
import com.entity.User;

public interface DashBoardService {
	
	void createOrder(OrderRequest orderRequest);

	Customers createCustomer(Customers customer);
	
	ServicesCategory createServices(ServicesCategory services);
	
	List<Customers> getALLCustomer();
	
	List<User> getAllUser();

}

