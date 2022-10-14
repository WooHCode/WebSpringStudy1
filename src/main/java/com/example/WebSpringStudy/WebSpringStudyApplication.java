package com.example.WebSpringStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebSpringStudyApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WebSpringStudyApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		SpringApplication.run(WebSpringStudyApplication.class, args);
	}

}
