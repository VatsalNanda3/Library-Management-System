package nanda.vatsal.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JsonBookMessageController {
	
	@Autowired
	private JsonBookKafkaProducer jsonBookKafkaProducer;
	
	@RequestMapping(method=RequestMethod.POST,value="/publishBookJSONMessage")
	
	//http://localhost:8080/publishMessage/?message=hello world
	public ResponseEntity<String> publish(@RequestBody Book book)
	{
		jsonBookKafkaProducer.sendMessage(book);
		return ResponseEntity.ok("Book JSON message sent to Kafka Topic");
	}

}
