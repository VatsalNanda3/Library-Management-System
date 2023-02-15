package nanda.vatsal.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.JsonBookKafkaProducer;

@Service
public class JsonLibKakfaProducer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(JsonBookKafkaProducer.class);
	
	@Autowired
	KafkaTemplate<String,Library> kafkaTemplate;
	
	public void sendMessage(Library data)
	{
		LOGGER.info(String.format("Library Message sent%s", data.toString()));
		Message<Library> message=MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "LibTopic")
				.build();
		
		kafkaTemplate.send(message);
				
	}

}
