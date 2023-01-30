//This is the book Entity which defines all the columns of the list_of_books table


package nanda.vatsal.books;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nanda.vatsal.user.User;

import javax.persistence.*;

@Entity
@Table(name="List_of_Books") //specifying the table name, by default it will choose the class name
public class Book {
	
	@Id // Used for primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
	//The @GeneratedValue annotation provides the specification of generation strategies for the primary keys values.
	// IDENTITY: In this case database is responsible for determining and assigning the next primary key.
	
	@Column(name="bookId")
	private int bookId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="isbn")
	private int isbn;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="price")
	private double price;
	
	
	
	
	public Book()
	{
		
	}

	public Book(int bookId, int quantity, int isbn, String title, String author, double price) {
		super();
		this.bookId = bookId;
		this.quantity = quantity;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
		
	
	
	
	

}
