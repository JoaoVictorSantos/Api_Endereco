package com.endereco;

import com.endereco.util.LoggerUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnderecoApplication {

	public static void main(String[] args) {
		LoggerUtil.logger.info("Iniciando a api de endereco");
		SpringApplication.run(EnderecoApplication.class, args);
	}

}
