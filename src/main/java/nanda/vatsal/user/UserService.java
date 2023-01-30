//service class for users to be used by the REST API for users

package nanda.vatsal.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;

@EnableAutoConfiguration

@Service 
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		List<User> users =new ArrayList<>();
		userRepository.findAll()
		.forEach(users::add); //Uses a lambda function to populate the list using the add method
		
		return users; 
		
		
		
	}
	
	public User getUserById(int id)
	{
		return userRepository.findById(id).orElse(null);
		
				
	}
	
	public User getUserByName(String name)
	{
		return userRepository.findByName(name);				
	}
	
	public void addUser(User user)
	{
		userRepository.save(user);
	}
	
	
	public User updateUser(User user, int id)
	{
		
		return userRepository.save(user);
	}
	
	public void deleteUser(int id)
	{
		userRepository.deleteById(id);
	}

}
