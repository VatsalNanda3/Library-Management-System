//REST API for the books table

package nanda.vatsal.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookService;
import nanda.vatsal.user.User;
import nanda.vatsal.user.UserNotFoundException;

@RestController
public class BookController {
	
	
	@Autowired
	public BookService bookService;
	
	@Autowired
	public BookRepository bookRepository;
	
	@RequestMapping("/books")
	//@Cacheable(value="Book")
	public List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}
	
	@RequestMapping("/booksById/{id}")
	@Cacheable(value="Book",key="#id")

	public Book getBookById(@PathVariable(value="id") Integer id) throws InterruptedException
	{
		
		Book Book=bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
		return bookService.getBookById(id);
		
				
	}
	
	@RequestMapping("/booksByTitle/{title}")
	@Cacheable(value="Book",key="#title")
	public Book getBookByTitle(@PathVariable(value="title") String title)
	{
		return bookService.getBookByTitle(title);				
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/books/addBook")
	public void addBook(@RequestBody Book book)
	{
		bookService.addBook(book);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/books/updateBook/{id}")
	@CachePut(value="Book", key="#id")
	public Book updateBook(@RequestBody Book book,@PathVariable(value="id") Integer id)
	{
		Book Book=bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
		Book bookEx=bookService.getBookById(id);
		bookEx.setAuthor(book.getAuthor());
		bookEx.setIsbn(book.getIsbn());
		bookEx.setPrice(book.getPrice());
		bookEx.setQuantity(book.getQuantity());
		bookEx.setTitle(book.getTitle());
		return bookService.updateBook(bookEx,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/books/deleteBook/{id}")
	@CacheEvict(value="Book", allEntries = true)
	public void deleteBook(@PathVariable(value="id") Integer id)
	{
		Book Book=bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
		bookService.deleteBook(id);
	}
	
	
	
	

}
