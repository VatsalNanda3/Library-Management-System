//REST API for the users table

package nanda.vatsal.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;


import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookService;

@RestController
public class UserController {
	
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;
	
	private ObjectMapper OBJECT_MAPPER=new ObjectMapper();
	
	@RequestMapping("/users")
	//@Cacheable(value="User")
	public List<User> getAllUsers()
	{
		
		return userService.getAllUsers();
	}
	
	@RequestMapping("/userById/{id}")
	@Cacheable(value = "User",key = "#id") 
	public User getUserById(@PathVariable(value="id") Integer id) 	{
		
		
		
		User userEx=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
		return userService.getUserById(id);
		
				
	}
	
	@RequestMapping("/userByName/{name}")
	@Cacheable(value="User",key="#name")
	public User getUserByName(@PathVariable(value="name") String name)
	{	
		
		return userService.getUserByName(name);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/users/addUser")
	public void addUser(@RequestBody User user) 
	{
		 userService.addUser(user);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/users/updateUser/{id}")
	@CachePut(value="User",key="#id")
	public User updateUser(@RequestBody User user,@PathVariable(value="id") Integer id)
	{
				
		User userEx=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
		userEx.setName(user.getName());
		userEx.setEmail(user.getEmail());
		userEx.setGender(user.getGender());
		userEx.setPassword(user.getPassword());
		userEx.setRole(user.getRole());
		return userService.updateUser(userEx,id);
		
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/users/deleteUser/{id}")
	@CacheEvict(value="User",allEntries = true)
	public void deleteUser(@PathVariable(value="id") Integer id)
	{
		User userEx=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
		userService.deleteUser(id);
	}
	
	
	
	

}
