package nanda.vatsal.reserve;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="List_of_ReservedBooks") //specifying the table name, by default it will choose the class name
public class Reserve  implements Serializable{
	
	@Id // Used for primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
	//The @GeneratedValue annotation provides the specification of generation strategies for the primary keys values.
	// IDENTITY: In this case database is responsible for determining and assigning the next primary key.
	
	@Column(name="resreveId")
	private int reserveId;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="userEmail")
	private String userEmail;
	
	@Column(name="userPhone")
	private String userPhone;
	
	@Column(name="bookId")
	private int bookId;
	
	@Column(name="bookName")
	private String bookName;
	
	public Reserve() {
		
	}

	public Reserve(int reserveId, int userId, String userName, String userEmail, String userPhone, int bookId,
			String bookName) {
		super();
		this.reserveId = reserveId;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.bookId = bookId;
		this.bookName = bookName;
	}

	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
}