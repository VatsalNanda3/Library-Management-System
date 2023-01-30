//REST API for Library class


package nanda.vatsal.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.books.BookService;

@RestController
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookRepository bookRepository; 
	
//	ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
//
//	If we want to use it, we have to return it from the endpoint; Spring takes care of the rest.
//
//	ResponseEntity is a generic type. Consequently, we can use any type as the response body:
	
	@RequestMapping("/lend/{userId}/{bookId}")
	public ResponseEntity<Library> lendBooksToUsers(@PathVariable int userId, @PathVariable int bookId) 
	{
		
		Book borrowBook=bookRepository.findById(bookId).get();
		if(borrowBook.getQuantity()>0)
		{
		Library libLend=libraryService.lendBooksToUsers(userId, bookId);
		 
		
		return new ResponseEntity<>(libLend, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping("/return/{borrowerId}/{bookId}")
	public ResponseEntity<Library> returnToLibrary(@PathVariable int borrowerId, @PathVariable int bookId)
	{
		
		Library libReturn=libraryService.returnToLibrary(borrowerId, bookId);
		return new ResponseEntity<>(libReturn, HttpStatus.OK);
	}

}
