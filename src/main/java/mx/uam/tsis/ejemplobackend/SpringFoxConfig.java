package mx.uam.tsis.ejemplobackend;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SpringFoxConfig {
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());                                           
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Mi primer API REST", 
	      "Ejemplo de API del curso TSIS", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Humberto Cervantes", "www.humbertocervantes.net", "hcm@xanum.uam.mx"), 
	      "License of API", "API license URL", Collections.emptyList());
    }
}
