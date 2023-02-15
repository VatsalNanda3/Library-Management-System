package nanda.vatsal.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class JsonResKafkaConsumer {
	
	
private static final Logger LOGGER=LoggerFactory.getLogger(JsonResKafkaConsumer.class);
	
	@Autowired
	private ReserveRepository reserveRepository;
	
	@KafkaListener(topics="ReserveTopic", groupId="LibraryTopic")
	public void consumeMessage(Reserve res)
	{
		LOGGER.info(String.format("Reserve Message Received%s", res.toString()));
		
		reserveRepository.save(res);
		
	}

}
