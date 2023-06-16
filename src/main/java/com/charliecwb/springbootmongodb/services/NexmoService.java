package com.charliecwb.springbootmongodb.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.nexmo.client.NexmoClient;
import com.nexmo.starter.NexmoCredentialsProperties;

@Service
public class NexmoService {
	@Bean
	public NexmoClient nexmoBuilder() {
		return new NexmoClient.Builder().apiKey("41990a8f")
				.apiSecret("62GJxyYTmj1gh6eO").build();
	}
}
