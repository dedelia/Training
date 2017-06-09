package training.app;

import java.util.Scanner;

import javax.persistence.Cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "training" })
// @PropertySource("classpath:application.properties")
public class Application {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(Application.class, args);
	}

}

