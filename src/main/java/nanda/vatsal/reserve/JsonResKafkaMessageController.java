package nanda.vatsal.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JsonResKafkaMessageController {
	
	
	@Autowired
	private JsonResKafkaProducer jsonresKafkaProducer;
	
	@RequestMapping(method=RequestMethod.POST,value="/publishResJSONMessage")
	
	
	public ResponseEntity<String> publish(@RequestBody Reserve res)
	{
		jsonresKafkaProducer.sendMessage(res);
		return ResponseEntity.ok("Reserve JSON message sent to Kafka Topic");
	}

}
