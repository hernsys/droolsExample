package com.plugtree.dslExample.entities;

public class Customer {

	private String name;
	private boolean registered;
	private Double expense;
	private Double discount;

	public Customer() {

	}

	public Customer(String name, boolean registered, Double expense) {
		this.name = name;
		this.registered = registered;
		this.expense = expense;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public Double getExpense() {
		return expense;
	}

	public void setExpense(Double expense) {
		this.expense = expense;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
