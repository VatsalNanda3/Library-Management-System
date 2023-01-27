package nanda.vatsal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nanda.vatsal.user.User;
import nanda.vatsal.user.UserRepository;

@EnableAutoConfiguration
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByName(username); //user object has to be converted to UserDetails
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found"); 
		}
		return new CustomUserDetails(user);
	}

}
