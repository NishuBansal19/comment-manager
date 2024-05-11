package com.intuit.commentmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommentManagerApplication {

	private static final Logger logger = LoggerFactory.getLogger(CommentManagerApplication.class);

	public static void main(String[] args) {
		logger.info("Start");
		SpringApplication.run(CommentManagerApplication.class, args);
	}

}
