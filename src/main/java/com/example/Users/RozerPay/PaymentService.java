package com.example.Users.RozerPay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.QrCode.QrCodeGenerater;
import com.google.zxing.WriterException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

	@Autowired
	private RazorpayClient razorpayClient;

	@Autowired
	private QrCodeGenerater codeGenerater;

	public String createOrder(int amount, String currency, String  receipt) throws RazorpayException, WriterException, IOException
	{
		JSONObject params=new JSONObject();
		// here we can also use Map String and Object pair
		
		params.put("amount", amount*100);
		params.put("currency", currency);
		params.put("receipt", receipt);
		
	
		Order order = razorpayClient.Orders.create( params);
		
		codeGenerater.generateQrCode(order.toString());
		
		return order.toString();
	}
	
	
	public Boolean  verifyPayment(String paymentId, String orderID, String signature)
	{
		JSONObject attribut=new JSONObject();
		attribut.put("razorpay_payment_id", paymentId);
		attribut.put("razorpay_order_id", orderID);
		attribut.put("razorpay_signature", signature);
		
		return razorpayClient.Payments.equals(attribut);
		
		
	}



}
