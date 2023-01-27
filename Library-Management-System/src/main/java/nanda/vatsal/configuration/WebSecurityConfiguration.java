package nanda.vatsal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAutoConfiguration
@Configuration //gives us the object of the classes 
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService; //interface of the spring security itself
	
	
	@Bean // this would return the object of the AuthenticationProvider
	AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		
		//DaoAuthenticationProvider is an AuthenticationProvider implementation that uses 
		//a UserDetailsService and PasswordEncoder to authenticate a username and password.
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		
		return provider;
		
		
	}
	


	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/users").access("hasAuthority('ADMIN')")
                .antMatchers("/userById/{id}").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/userByName/{name}").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/users/addUser").access("hasAuthority('ADMIN')")
                .antMatchers("/users/updateUser/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/users/deleteUser/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/books").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/booksById/{id}").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/booksByTitle/{title}").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/books/addBook").access("hasAuthority('ADMIN')")
                .antMatchers("/books/updateBook/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/books/deleteBook/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/lend/{userId}/{bookId}").access("hasAuthority('ADMIN')")
                .antMatchers("/return/{borrowerId}/{bookId}}").access("hasAuthority('ADMIN')")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        		http.cors().disable().csrf().disable();//Added for POST, PUT and DELETE methods
        
        
    }	

}
