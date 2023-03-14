package br.com.bling.ApiContatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2

public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.bling.ApiContatos")).paths(PathSelectors.regex("/api.*"))
				.build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "API REST Contatos",
                "API REST para consumo de contatos Bling.",
                "1.0",
                "Terms of Service",
                new Contact("Okeaa", "http://www.okeaatecnologia.com/", "rafael.machado@okeaatecnologia.com.br"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

}
