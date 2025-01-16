package com.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.entity.Customers;
import com.entity.Orders;
import com.entity.ServicesCategory;
import com.entity.User;
import com.repo.CustomerReposirtory;
import com.repo.OrderRepository;
import com.repo.ServicesRepostory;
import com.repo.UserRepository;
import com.service.DashBoardService;
import com.service.OrderRequest;
@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	private CustomerReposirtory customerReposirtory;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ServicesRepostory servicesRepostory;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Customers createCustomer(Customers customer) {
		return customerReposirtory.save(customer);	
	}

	public void createOrder(OrderRequest orderRequest) {

		Customers customer = customerReposirtory.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		User user = userRepository.findById(orderRequest.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		List<ServicesCategory> servicesCategories = servicesRepostory.findAllById(orderRequest.getServiceIds());
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setCustomerId(orderRequest.getCustomerId());
		order.setUserId(orderRequest.getUserId());
		order.setUser(user);
		order.setServices(servicesCategories);
		order.setTotalBill(orderRequest.getTotalBill());
		order.setMessage(orderRequest.getMessage());
		 orderRepository.save(order);
	}

	@Override
	public List<Customers> getALLCustomer() {
		// TODO Auto-generated method stub
		return customerReposirtory.findAll() ;
	}

	@Override
	public ServicesCategory createServices(ServicesCategory services) {
		// TODO Auto-generated method stub
		return servicesRepostory.save(services);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	
}
