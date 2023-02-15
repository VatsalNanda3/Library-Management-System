package nanda.vatsal.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import nanda.vatsal.books.JsonBookKafkaProducer;

@Service
public class JsonLibKakfaConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(JsonBookKafkaProducer.class);
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	
	@KafkaListener(topics="LibTopic",groupId="LibraryGroup")
	public void consume(Library library)
	{
		LOGGER.info(String.format("Library Message Consumed %s", library.toString()));
		
		libraryRepository.save(library);
	}
	

}
