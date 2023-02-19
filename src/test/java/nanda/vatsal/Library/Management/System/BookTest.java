package nanda.vatsal.Library.Management.System;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.books.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@AfterEach
	public void afterSetup() {
		bookRepository.deleteAll();
	}

	@Test
	public void getAllBooksTest() {

		when(bookRepository.findAll()).thenReturn(Stream.of(new Book(22,3,1234566,"Test_Title","Test_Author",250.00),new Book(24,5,448546464,"Test_Title2","Test_Author2",260.00)).collect(Collectors.toList()));
		//this means that when the findAll function is called, we return the list of records instead of hitting the database

		assertEquals(2,bookService.getAllBooks().size());//compare the size of both the expected and actual list from service layer
	}

	@Test
	public void getBookByIdTest() {
		Book book=new Book(22,3,1234566,"Test_Title","Test_Author",250.00);
		bookRepository.save(book);
		assertNotNull(book);
		int id=22;
		assertEquals(id,book.getBookId());

	}

	@Test
	public void getBookByTitleTest() {
		Book book=new Book(22,3,1234566,"Test_Title","Test_Author",250.00);
		bookRepository.save(book);
		assertNotNull(book);
		String title="Test_Title";
		assertEquals(title,book.getTitle());
	}

	@Test
	public void updateBookTest() {
		Book book=new Book(22,3,1234566,"Test_Title","Test_Author",250.00);
		if(book.getBookId()==22)
		{
			 book=new Book(23,3,1234566,"Test_Title","Test_Author",250.00);
			 bookRepository.save(book);
			 assertNotNull(book);
			 assertEquals(book.getBookId(),23);
		}
	}

	@Test
	public void addBookTest() {
		Book book=new Book(22,3,1234566,"Test_Title","Test_Author",250.00);
		when(bookRepository.save(book)).thenReturn(book);
		assertNotNull(book);




	}

	@Test
	public void deleteBookTest()
	{
		Book book=new Book(22,3,1234566,"Test_Title","Test_Author",250.00);
		bookRepository.delete(book);


		verify(bookRepository, times(1)).delete(book);//check how many times the method is called
	}







}

