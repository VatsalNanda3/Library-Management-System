// Service to be used for Library REST API

package nanda.vatsal.lib;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.payment.Order;
import nanda.vatsal.payment.PayPalService;
import nanda.vatsal.user.User;
import nanda.vatsal.user.UserRepository;

@EnableAutoConfiguration

@Service 
public class LibraryService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	LibraryRepository libraryRepository;
	
	
	
	public Library lendBooksToUsers(int userId,int bookId)  
	{
		
		Order ord=new Order();
		Book borrowBook=bookRepository.findById(bookId).get();
		
		User borrowUser=userRepository.findById(userId).get();
		
		Library lib=new Library();
		
		
		lib.setBook_id(borrowBook.getBookId());
		lib.setBook_issued(borrowBook.getTitle());
		lib.setUser_name(borrowUser.getName());
		lib.setEmail(borrowUser.getEmail());
		lib.setBorrow_date(LocalDate.now());
		lib.setActual_return_date(LocalDate.now().plusDays(7));
		
		borrowBook.setQuantity(borrowBook.getQuantity()-1);
		
		
		
		libraryRepository.save(lib);
		
		return lib;
		
		
	}
	
	public Library returnToLibrary(int borrowerId, int bookId)
	{
		
		Library lendedBook=libraryRepository.findById(borrowerId).get();
		
		Library bookReturned=new Library();
		
		lendedBook.setReturn_date(LocalDate.now());

		
		Book borrowBook=bookRepository.findById(bookId).get();
		

		LocalDate issue_date=lendedBook.getBorrow_date();
		
		
		
	    LocalDate return_date=lendedBook.getReturn_date();
	    
	
	    
	    
		int elapsedDays = (int)ChronoUnit.DAYS.between(issue_date,return_date);
		
		
		
		if(elapsedDays>7)
			lendedBook.setFine((elapsedDays-7)*5);
		else
			lendedBook.setFine(0);
		
		int val=borrowBook.getQuantity()+1;
		borrowBook.setQuantity(val);
		
		libraryRepository.save(lendedBook);
		return lendedBook;
		
	}

}
