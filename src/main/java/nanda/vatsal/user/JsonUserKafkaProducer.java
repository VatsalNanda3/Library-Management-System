package nanda.vatsal.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonUserKafkaProducer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JsonUserKafkaProducer.class);
	
	@Autowired
	KafkaTemplate<String,User> kafkaTemplate;
	
	
	public void sendMessage(User data)
	{
		LOGGER.info(String.format("User message sent %s", data.toString()));
		Message<User> message=MessageBuilder
				.withPayload(data)
				.setHeaderIfAbsent(KafkaHeaders.TOPIC, "UserTopic")
				.build();
		
		kafkaTemplate.send(message);
		
	}
	

}
