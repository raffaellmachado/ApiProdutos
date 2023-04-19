//package br.com.bling.ApiDepositos.config;
//
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"br.com.bling.ApiDepositos.repositories"})
//@EntityScan(basePackages = {"br.com.bling.ApiDepositos.controllers.response", "br.com.bling.ApiDepositos.controllers.request"})
//public class ApplicationConfig {
//
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
//
//}
