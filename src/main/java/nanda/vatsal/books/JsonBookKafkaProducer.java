package nanda.vatsal.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class JsonBookKafkaProducer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(JsonBookKafkaProducer.class);
	
	@Autowired
	KafkaTemplate<String,Book> kafkaTemplate;
	
	
	
	public void sendMessage(Book data)
	{
		LOGGER.info(String.format("Book Message sent  %s", data.toString()));
		Message<Book> message=MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC,"BookTopic")
				.build(); 
		kafkaTemplate.send(message);
	}
	

}
