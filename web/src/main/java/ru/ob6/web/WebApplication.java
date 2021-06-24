package ru.ob6.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import ru.ob6.impl.ImplApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Import(ImplApplication.class)
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
