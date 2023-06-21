package br.com.bling.ApiFormaPagamento.config;//package br.com.bling.ApiCategorias.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@EnableJpaRepositories(basePackages = {"br.com.bling.ApiFormaPagamento.repositories"})
@EntityScan(basePackages = {"br.com.bling.ApiFormaPagamento.controllers.response", "br.com.bling.ApiFormaPagamento.controllers.request"})
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
