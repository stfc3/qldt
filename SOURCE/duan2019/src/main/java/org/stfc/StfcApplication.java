package org.stfc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.stfc.dto.CertificateOfficers;
import org.stfc.repository.impl.OfficersRepositoryImpl;
import org.stfc.utils.Comparator;

import com.google.gson.Gson;

@SpringBootApplication
@EnableScheduling
public class StfcApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(StfcApplication.class);
	@Autowired
	OfficersRepositoryImpl repository;

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
