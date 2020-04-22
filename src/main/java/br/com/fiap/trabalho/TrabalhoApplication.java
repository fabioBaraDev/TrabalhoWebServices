package br.com.fiap.trabalho;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.fiap.trabalho.service.LoadBaseFileService;

@SpringBootApplication
public class TrabalhoApplication {

	public static void main(String[] args) throws IOException {

		ConfigurableApplicationContext context = SpringApplication.run(TrabalhoApplication.class, args);
		context.getBean(LoadBaseFileService.class).load();
	}

}
