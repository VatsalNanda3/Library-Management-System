package nanda.vatsal.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import nanda.vatsal.lib.Library;
import nanda.vatsal.lib.LibraryRepository;

@Controller
public class PayPalController {
	
	
	@Autowired
	PayPalService service;
	
	@Autowired
	LibraryRepository libraryRepository;
	
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	
	@RequestMapping("/payHome")
	public String payHome()
	{
		return "payHome";
	}


	@RequestMapping(method=RequestMethod.POST,value="/pay")
	public String payment(@ModelAttribute("order") Order order) {
		try {
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(),order.getBorrowerId(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			int value=order.getBorrowerId();
			int money=(int)order.getPrice();
			money*=80;//coverting USD to INR, as PayPal uses USD
			
			//Basically updates the fine vaue in the SQL table by subtracting the amount paid by the user
			Library lendedBook=libraryRepository.findById(value).get();
			lendedBook.setFine(lendedBook.getFine()-money);
			libraryRepository.save(lendedBook);
						for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @RequestMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "cancel";
	    }

	    @RequestMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }


}
