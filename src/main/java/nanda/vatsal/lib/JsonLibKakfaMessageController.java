package nanda.vatsal.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonLibKakfaMessageController {

	@Autowired
	private JsonLibKakfaProducer jsonLibKafkaProducer;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/publishLibraryJSONMessage")
	public ResponseEntity<String> publish(@RequestBody Library library)
	{
		jsonLibKafkaProducer.sendMessage(library);
		
		return ResponseEntity.ok("Library JSON Message Sent to Kafka Topic");
		
	}
	
}
