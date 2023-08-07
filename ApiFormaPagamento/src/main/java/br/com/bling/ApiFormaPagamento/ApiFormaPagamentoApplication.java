package br.com.bling.ApiFormaPagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiFormaPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFormaPagamentoApplication.class, args);
	}

}
