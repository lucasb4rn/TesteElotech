package br.com.lucas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InicioApplication {

	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/elotech");
		SpringApplication.run(InicioApplication.class, args);
	}

}
