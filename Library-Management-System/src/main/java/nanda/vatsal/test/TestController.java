package nanda.vatsal.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	@RequestMapping("/")
	public String test() {
		return "Welcome to Library Management System";
	}

}
