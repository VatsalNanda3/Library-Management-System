package nanda.vatsal.reserve;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class JsonResKafkaTopic {
	
	@Bean
	public NewTopic topic_Reserve()
	{
		return TopicBuilder.name("ReserveTopic")
				.build();
	}

}
