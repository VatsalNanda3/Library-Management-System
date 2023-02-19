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

import nanda.vatsal.user.User;
import nanda.vatsal.user.UserRepository;
import nanda.vatsal.user.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@AfterEach
	public void afterSetup() {
		userRepository.deleteAll();
	}

	@Test
	public void getAllUsersTest() {

		when(userRepository.findAll()).thenReturn(Stream.of(new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone"),new User(13,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone")).collect(Collectors.toList()));
		//this means that when the findAll function is called, we return the list of records instead of hitting the database

		assertEquals(2,userService.getAllUsers().size());//compare the size of both the expected and actual list from service layer
	}

	@Test
	public void getUserByIdTest() {
		User user=new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
		userRepository.save(user);
		assertNotNull(user);
		int id=12;
		assertEquals(id,user.getId());

	}

	@Test
	public void getUserByNameTest() {
		User user=new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
		userRepository.save(user);
		assertNotNull(user);
		String name="Test_Name";
		assertEquals(name,user.getName());
	}

	@Test
	public void updateUserTest() {
		User user=new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
		if(user.getId()==12)
		{
			 user=new User(13,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
			 userRepository.save(user);
			 assertNotNull(user);
			 assertEquals(user.getId(),13);
		}
	}

	@Test
	public void addUserTest() {
		User user=new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
		when(userRepository.save(user)).thenReturn(user);
		assertNotNull(user);




	}

	@Test
	public void deleteUserTest()
	{
		User user=new User(12,"Test_Name","abc_test@gmail.com","Test_pwd","Test_Gender","Test_Role","Test_Phone");
		userRepository.delete(user);


		verify(userRepository, times(1)).delete(user);//check how many times the method is called
	}

}
