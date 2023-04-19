package br.com.bling.ApiDepositos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = {"br.com.bling.ApiDepositos.repositories"})
@EntityScan(basePackages = {"br.com.bling.ApiDepositos.controllers.response", "br.com.bling.ApiDepositos.controllers.request"})
public class ApiDepositoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDepositoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
