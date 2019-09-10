package org.stfc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.stfc.repository.impl.DashboardServices;

import com.google.gson.Gson;

@SpringBootApplication
@EnableAutoConfiguration
public class StfcApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(StfcApplication.class);
	@Autowired
	DashboardServices services;

	public static void main(String[] args) {
		SpringApplication.run(StfcApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Override
	public void run(String... strings) throws Exception {
	}

}
