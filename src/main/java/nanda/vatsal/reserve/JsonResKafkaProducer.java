package nanda.vatsal.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class JsonResKafkaProducer {
	
private static final Logger LOGGER=LoggerFactory.getLogger(JsonResKafkaProducer.class);
	
	@Autowired
	KafkaTemplate<String,Reserve> kafkaTemplate;
	
	
	public void sendMessage(Reserve reserve)
	{
		LOGGER.info(String.format("Reserve message sent %s", reserve.toString()));
		Message<Reserve> message=MessageBuilder
				.withPayload(reserve)
				.setHeaderIfAbsent(KafkaHeaders.TOPIC, "ReserveTopic")
				.build();
		
		kafkaTemplate.send(message);
		
	}

}
