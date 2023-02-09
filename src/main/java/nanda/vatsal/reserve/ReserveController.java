package nanda.vatsal.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookNotFoundException;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.lib.Library;
import nanda.vatsal.user.User;
import nanda.vatsal.user.UserNotFoundException;
import nanda.vatsal.user.UserRepository;

@RestController
public class ReserveController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReserveService resreveService;
	
	@RequestMapping("/reserve/{userId}/{bookId}")
	@Cacheable(value="Reserve", key="#userId")
	public Reserve reserveBook(@PathVariable(value="userId") Integer userId, @PathVariable Integer bookId) 
	{
		Book book=bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));
		User userEx=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID " + userId));
		Reserve res=null;
		if(book.getQuantity()==0)
		{
			res=resreveService.reserveBook(userId, bookId);
			
		}
		return res;
		
		
		
		
	}

}
