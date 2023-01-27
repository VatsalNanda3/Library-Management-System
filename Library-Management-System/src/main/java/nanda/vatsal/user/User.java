package nanda.vatsal.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import nanda.vatsal.books.Book;

@Entity
@Table(name="list_of_users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="role")
	private String role;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="fk_user_id",referencedColumnName="userId",updatable=true)
//	private List<Book> book=new ArrayList<>();
	

	
	

	
	
	
	public User(int id, String name, String email, String password, String gender, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		
	}


	public User()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


//	public List<Book> getBook() {
//		return book;
//	}
//
//
//	public void setBook(List<Book> book) {
//		this.book = book;
//	}
	
	
	
	
	
	
	
	
	

}
