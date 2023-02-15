package nanda.vatsal.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonUserMessageController {
	
	@Autowired
	private JsonUserKafkaProducer jsonUserKafkaProducer;
	
	@RequestMapping(method=RequestMethod.POST,value="/publishUserJSONMessage")
	
	
	public ResponseEntity<String> publish(@RequestBody User user)
	{
		jsonUserKafkaProducer.sendMessage(user);
		return ResponseEntity.ok("User JSON message sent to Kafka Topic");
	}

}
