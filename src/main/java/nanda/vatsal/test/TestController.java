package nanda.vatsal.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Amount;

import nanda.vatsal.payment.Order;

@RestController
public class TestController {
	
	
	@RequestMapping("/")
	public String test(@ModelAttribute Order order) {
		
		System.out.println(order.toString());
		
		
		return "Welcome to Library Management System";
		
		
		
	}

}
