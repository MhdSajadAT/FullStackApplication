package com.service;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {
	
	private Integer customerId;
	private Integer userId;
	private List<Integer> serviceIds;
	private BigDecimal totalBill;
	private String message;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Integer> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(List<Integer> serviceIds) {
		this.serviceIds = serviceIds;
	}
	public BigDecimal getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
