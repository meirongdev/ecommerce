package dev.meirong.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI ecommerceOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("E-commerce API")
						.version("1.0")
						.description("API for an e-commerce application"));
	}
}
