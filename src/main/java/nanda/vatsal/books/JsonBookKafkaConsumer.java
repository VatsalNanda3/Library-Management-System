package nanda.vatsal.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;




@Service
public class JsonBookKafkaConsumer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(JsonBookKafkaConsumer.class);
	
	@Autowired 
	BookRepository bookRepository;
	@KafkaListener(topics="BookTopic",groupId="LibraryGroup")//From the topic New_Topic_JSON, the message is receieved and the consumer groupID is myGroup
	public void consume(Book book) {
		LOGGER.info(String.format("Json Message received %s", book.toString()));
		
		
		bookRepository.save(book);
		
		
		
	}

}
