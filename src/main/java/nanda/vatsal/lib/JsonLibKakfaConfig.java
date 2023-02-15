package nanda.vatsal.lib;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class JsonLibKakfaConfig {
	
	@Bean
	public NewTopic topicLibrary()
	{
		return TopicBuilder.name("LibTopic")
				.build();
	}

}
