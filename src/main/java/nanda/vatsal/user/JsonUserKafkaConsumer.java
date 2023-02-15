package nanda.vatsal.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonUserKafkaConsumer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JsonUserKafkaConsumer.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@KafkaListener(topics="UserTopic", groupId="LibraryTopic")
	public void consumeMessage(User user)
	{
		LOGGER.info(String.format("User Message Received%s", user.toString()));
		
		userRepository.save(user);
		
	}
	

}
