package nanda.vatsal.email;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nanda.vatsal.books.Book;
import nanda.vatsal.lib.*;

@EnableScheduling
@RestController
public class EmailSenderController {
	
	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private LibraryRepository libraryRepository;
		
	

	
	Library lib=new Library();
	@Scheduled(cron = "0 0 10 * * *")//sends a daily mail at 10:00 AM for fine return, if applicable
	
	public void getAllRecords()
	{
		System.out.println("Running");
		List<Library> lib =new ArrayList<>();
		libraryRepository.findAll()
		.forEach(lib::add); //Uses a lambda function to populate the list using the add method
		
	  for(Library library:lib)
	  {
		  if(library.getFine()>0)
		  {
			  senderService.sendEmail(library.getEmail(),
						"Dear "+library.getUser_name()+
						", Your fine amount, Rs. "+library.getFine()+", is due."+"Kindly pay it at the earliest."
						,
						"Fine Due "+library.getUser_name());

			}
			  
		  }
	  }
		
	}
	
	
	
	

