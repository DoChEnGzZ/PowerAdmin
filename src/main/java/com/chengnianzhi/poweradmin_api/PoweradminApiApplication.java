package com.chengnianzhi.poweradmin_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PoweradminApiApplication {

	private static final Logger log = LoggerFactory.getLogger(PoweradminApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PoweradminApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			log.debug("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				log.debug("Bean: {}", beanName);
			}
			log.info("Active Profiles {}", Arrays.toString(ctx.getEnvironment().getActiveProfiles()));
		};

	}
}
