package nanda.vatsal.Library.Management.System;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;



import nanda.vatsal.email.EmailSenderService;


@SpringBootApplication(scanBasePackages= {"nanda.vatsal.test","nanda.vatsal.Library.Management.System","nanda.vatsal.books","nanda.vatsal.user","nanda.vatsal.lib","nanda.vatsal.email","nanda.vatsal.payment","nanda.vatsal.configuration","nanda.vatsal.redisConfig","nanda.vatsal.reserve"})
@EnableCaching
public class LibraryManagementSystemApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	
	}
	

	
		
	
	
	


}
