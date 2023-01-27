package nanda.vatsal.lib;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Library_Details")
public class Library {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="borrower_id")
	private int borrower_id;
	
	@Column(name="book_id")
	private int book_id;
	
	@Column(name="Username")
	private String user_name;
	
	@Column(name="Book_Issued")
	private String book_issued;
	
	@Column(name="Borrow_Date")
	private LocalDate borrow_date;
	
	@Column(name="Return_Date")
	private LocalDate return_date;
	
	@Column(name="Actual_Return_Date")
	private LocalDate actual_return_date;
	
	
	@Column(name="fine")
	private int fine;
	
	public Library() {
		
	
	}
	
	

	public Library(int borrower_id, int book_id, String user_name, String book_issued, LocalDate borrow_date,
			LocalDate return_date, LocalDate actual_return_date, boolean available, int fine) {
		super();
		this.borrower_id = borrower_id;
		this.book_id = book_id;
		this.user_name = user_name;
		this.book_issued = book_issued;
		this.borrow_date = borrow_date;
		this.return_date = return_date;
		this.actual_return_date = actual_return_date;
		this.fine = fine;
	}



	public int getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBook_issued() {
		return book_issued;
	}

	public void setBook_issued(String book_issued) {
		this.book_issued = book_issued;
	}

	public LocalDate getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(LocalDate borrow_date) {
		this.borrow_date = borrow_date;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}

	public LocalDate getActual_return_date() {
		return actual_return_date;
	}

	public void setActual_return_date(LocalDate actual_return_date) {
		this.actual_return_date = actual_return_date;
	}


	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}
	
	
	
}

	