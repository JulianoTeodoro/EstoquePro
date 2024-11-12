package EstoquePro.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
		security = {
				@SecurityRequirement(name = "bearerAuth")
		}
)
@SecurityScheme
(
		name="bearerAuth",
		description = "JWT Bearer Auth",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customAPI() {
		return new OpenAPI().info(new Info().title("API.EstoquePro").version("1.0.0")
				.license(new License().name("API responsavel pelo gerenciamento de estoque de uma empresa de venda de componentes de hardware.")));
	}
}