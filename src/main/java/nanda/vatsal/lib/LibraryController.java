//REST API for Library class


package nanda.vatsal.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookNotFoundException;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.books.BookService;
import nanda.vatsal.email.EmailSenderService;
import nanda.vatsal.reserve.Reserve;
import nanda.vatsal.reserve.ReserveRepository;
import nanda.vatsal.reserve.ReserveService;
import nanda.vatsal.user.User;
import nanda.vatsal.user.UserNotFoundException;
import nanda.vatsal.user.UserRepository;

@RestController
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookRepository bookRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private ReserveRepository resreveRepository;
	
	@Autowired
	private ReserveService reserveService;
	
	@Autowired
	private EmailSenderService senderService;
	

	@RequestMapping("/lend/{userId}/{bookId}")
	@Cacheable(value="Lend", key="#userId")
	public Library lendBooksToUsers(@PathVariable(value="userId") Integer userId, @PathVariable Integer bookId) 
	{
		Book book=bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));
		User userEx=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID " + userId));
		Book borrowBook=bookRepository.findById(bookId).get();
		Library libLend=null;
		
		if(borrowBook.getQuantity()>0)
		{
			libLend=libraryService.lendBooksToUsers(userId, bookId);
			
		}
		return libLend;
		
	}
	
	@RequestMapping("/return/{borrowerId}/{bookId}")
	@Cacheable(value="return",key="#borrowerId")
	public Library returnToLibrary(@PathVariable(value="borrowerId") Integer borrowerId, @PathVariable Integer bookId)
	{
		Library lib=libraryRepository.findById(borrowerId).orElseThrow(() -> new BorrowerNotFoundException("Borrower not found with ID " + borrowerId));
		Book book=bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));
		
		Library libReturn=libraryService.returnToLibrary(borrowerId, bookId);
		
		Reserve res=resreveRepository.findById(bookId).get();
		if(bookId==res.getBookId())
		{
			senderService.sendEmail(res.getUserEmail(),
					"Dear "+res.getUserName()+ ", the book with book Id "+res.getBookId()+" and book name "+res.getBookName()+"is now available in the library.Kindly issue it at the earliest. "
					,
					"Book available  "+res.getUserName());
			reserveService.deleteReserve(res.getReserveId());
		}
		

		return libReturn;
	}

}
