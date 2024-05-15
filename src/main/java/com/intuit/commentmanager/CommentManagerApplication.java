package com.intuit.commentmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.intuit.commentmanager"})
@EnableCaching
@EnableJpaAuditing
public class CommentManagerApplication {

	private static final Logger logger = LoggerFactory.getLogger(CommentManagerApplication.class);

	public static void main(String[] args) {
		logger.info("Start");
		SpringApplication.run(CommentManagerApplication.class, args);
	}

}
