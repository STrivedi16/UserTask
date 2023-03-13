package com.example.Users.RozerPay;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.razorpay.RazorpayException;
@RestController
public class RazorController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/pay/createOrder")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) throws RazorpayException, WriterException, IOException {
        String orderId = paymentService.createOrder(orderRequest.getAmount(), orderRequest.getCurrency(),
                orderRequest.getReceipt());
        return ResponseEntity.ok(orderId);
    }
	
	@PostMapping("/verifyPayment")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentRequest paymentRequest) throws RazorpayException {
		
		
        boolean result = paymentService.verifyPayment(paymentRequest.getPaymentId(), paymentRequest.getOrderId(),
                paymentRequest.getSignature());
        
        
        if (result) {
        	
            return ResponseEntity.ok("Payment Successful");
        } 
        else {
            return ResponseEntity.ok("Payment Failed");
        }
    }
}
