package com.app.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.app.todo.entity.ToDo;

@Configuration
public class Config {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory lcf = new LettuceConnectionFactory();
		lcf.afterPropertiesSet();
		return lcf;
	}

	@Bean
	public RedisTemplate<String, ToDo> redisTemplate() {
		RedisTemplate<String, ToDo> redisTemplate = new RedisTemplate<String, ToDo>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
