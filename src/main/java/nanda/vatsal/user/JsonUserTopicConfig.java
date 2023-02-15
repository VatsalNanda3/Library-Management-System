package nanda.vatsal.user;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class JsonUserTopicConfig {
	
	@Bean
	public NewTopic topic_User()
	{
		return TopicBuilder.name("UserTopic")
				.build();
	}

}
