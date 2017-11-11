package com.ronvel.farztev.admin.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
}
