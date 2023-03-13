package com.example.Users.RozerPay;

public class PaymentRequest {

	private String paymentId;
	
	private String orderId;
	
	private String signature;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	public PaymentRequest(String paymentId, String orderId, String signature) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
