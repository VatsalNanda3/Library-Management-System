package nanda.vatsal.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class UserController {
	
	
	@Autowired
	public UserService userService;
	
	@Autowired
	
	public BookService bookService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@RequestMapping("/userById/{id}")
	public User getUserById(@PathVariable int id)
	{
		return userService.getUserById(id);
		
				
	}
	
	@RequestMapping("/userByName/{name}")
	public User getUserByName(@PathVariable String name)
	{
		return userService.getUserByName(name);				
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/users/addUser")
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/users/updateUser/{id}")
	public User updateUser(@RequestBody User user,@PathVariable int id)
	{
				
		User userEx=userService.getUserById(id);
		userEx.setName(user.getName());
		userEx.setEmail(user.getEmail());
		userEx.setGender(user.getGender());
		userEx.setPassword(user.getPassword());
		userEx.setRole(user.getRole());
		return userService.updateUser(userEx,id);
		
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/users/deleteUser/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);
	}
	
	
	
	

}
