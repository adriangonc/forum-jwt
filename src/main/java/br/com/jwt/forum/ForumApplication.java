package br.com.jwt.forum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ForumApplication {

	private static Logger logger = LoggerFactory.getLogger(ForumApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando API");
		SpringApplication.run(ForumApplication.class, args);
		logger.info("API iniciada com sucesso!");
	}

}
