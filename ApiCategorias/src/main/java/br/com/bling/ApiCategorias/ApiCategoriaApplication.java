package br.com.bling.ApiCategorias;

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
@EnableJpaRepositories(basePackages = {"br.com.bling.ApiCategorias.repositories"})
@EntityScan(basePackages = {"br.com.bling.ApiCategorias.controllers.response", "br.com.bling.ApiCategorias.controllers.request"})
public class ApiCategoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCategoriaApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
