package nanda.vatsal.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;

@EnableAutoConfiguration

@Service 
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks()
	{
		List<Book> books =new ArrayList<>();
		bookRepository.findAll()
		.forEach(books::add); //Uses a lambda function to populate the list using the add method
		
		return books; 
		
		
		
	}
	
	public Book getBookById(int id)
	{
		return bookRepository.findById(id).orElse(null);
		
				
	}
	
	public Book getBookByTitle(String title)
	{
		return bookRepository.findByTitle(title);				
	}
	
	public void addBook(Book book)
	{
		bookRepository.save(book);
	}
	
	
	public Book updateBook(Book book, int id)
	{
		
		return bookRepository.save(book);
	}
	
	public void deleteBook(int id)
	{
		bookRepository.deleteById(id);
	}

}
