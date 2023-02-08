package nanda.vatsal.redisConfig;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration //tells that there are beans in this class
@EnableRedisRepositories
public class RedisConfig {


	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    RedisStandaloneConfiguration redisStandadloneConfiguration=new RedisStandaloneConfiguration();
	    
	    redisStandadloneConfiguration.setHostName("localhost");
	    redisStandadloneConfiguration.setPort(6379);
	    
	    return new JedisConnectionFactory(redisStandadloneConfiguration);
	    
	}
	
	 @Bean
	 public RedisTemplate<String, Object> createRedisTemplateForEntity() {
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
	        redisTemplate.setConnectionFactory(jedisConnectionFactory());
	        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setValueSerializer(new StringRedisSerializer());
	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	        redisTemplate.afterPropertiesSet();

	    return redisTemplate;
	}
	
	

}
