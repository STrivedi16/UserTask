package com.example.Users.RozerPay;

public class OrderRequest {

	
	private int amount;
    private String currency;
    private String receipt;
    
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	public OrderRequest(int amount, String currency, String receipt) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.receipt = receipt;
	}
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
